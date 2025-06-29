package cn.edu.scnu.ssyx.activity.service.impl;
import cn.edu.scnu.ssyx.activity.mapper.ActivityInfoMapper;
import cn.edu.scnu.ssyx.activity.mapper.ActivityRuleMapper;
import cn.edu.scnu.ssyx.activity.mapper.ActivitySkuMapper;
import cn.edu.scnu.ssyx.activity.service.ActivityInfoService;
import cn.edu.scnu.ssyx.activity.service.CouponInfoService;
import cn.edu.scnu.ssyx.client.product.ProductFeignClient;
import cn.edu.scnu.ssyx.enums.ActivityType;
import cn.edu.scnu.ssyx.model.activity.ActivityInfo;
import cn.edu.scnu.ssyx.model.activity.ActivityRule;
import cn.edu.scnu.ssyx.model.activity.ActivitySku;
import cn.edu.scnu.ssyx.model.product.SkuInfo;
import cn.edu.scnu.ssyx.vo.activity.ActivityRuleVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

// 活动表 服务类
@Service
public class ActivityInfoServiceImpl extends ServiceImpl<ActivityInfoMapper, ActivityInfo> implements ActivityInfoService {

    @Resource
    private ActivityRuleMapper activityRuleMapper;

    @Resource
    private ActivitySkuMapper activitySkuMapper;
    @Resource
    private ProductFeignClient productFeignClient;
    @Resource
    private CouponInfoService couponInfoService;

    //优惠活动列表方法
    @Override
    public IPage<ActivityInfo> selectPage(Page<ActivityInfo> pageParam) {
        QueryWrapper<ActivityInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        IPage<ActivityInfo> page = baseMapper.selectPage(pageParam, queryWrapper);
        page.getRecords().stream().forEach(item -> {
            item.setActivityTypeString(item.getActivityType().getComment());
        });
        return page;
    }

    //1 根据活动id获取活动规则数据
    @Override
    public Map<String, Object> findActivityRuleList(Long id) {
        Map<String, Object> result = new HashMap<>();
        //1 根据活动id查询，查询规则列表 activity_rule表
        LambdaQueryWrapper<ActivityRule> wrapperActivityRule = new LambdaQueryWrapper<>();
        wrapperActivityRule.eq(ActivityRule::getActivityId,id);
        List<ActivityRule> activityRuleList = activityRuleMapper.selectList(wrapperActivityRule);
        result.put("activityRuleList",activityRuleList);

        //2 根据活动id查询，查询使用规则商品skuid列表 activity_sku表
        List<ActivitySku> activitySkuList = activitySkuMapper.selectList(
                new LambdaQueryWrapper<ActivitySku>().eq(ActivitySku::getActivityId, id)
        );
        //获取所有skuId
        List<Long> skuIdList =
                activitySkuList.stream().map(ActivitySku::getSkuId).collect(Collectors.toList());
        //2.1 通过远程调用 service-product模块接口，根据 skuid列表 得到商品信息
        List<SkuInfo> skuInfoList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(skuIdList)) {
            skuInfoList = productFeignClient.findSkuInfoList(skuIdList);
        }
        result.put("skuInfoList",skuInfoList);

        return result;
    }

    //2 在活动里面添加规则数据
    @Override
    public void saveActivityRule(ActivityRuleVo activityRuleVo) {
        //第一步 根据活动id删除之前规则数据
        //ActivityRule数据删除
        Long activityId = activityRuleVo.getActivityId();
        activityRuleMapper.delete(
                new LambdaQueryWrapper<ActivityRule>().eq(ActivityRule::getActivityId,activityId)
        );
        //ActivitySku数据删除
        activitySkuMapper.delete(
                new LambdaQueryWrapper<ActivitySku>().eq(ActivitySku::getActivityId,activityId)
        );

        //第二步 获取规则列表数据
        List<ActivityRule> activityRuleList = activityRuleVo.getActivityRuleList();
        ActivityInfo activityInfo = baseMapper.selectById(activityId);
        for (ActivityRule activityRule:activityRuleList) {
            activityRule.setActivityId(activityId);//活动id
            activityRule.setActivityType(activityInfo.getActivityType());//类型
            activityRuleMapper.insert(activityRule);
        }

        //第三步 获取规则范围数据
        List<ActivitySku> activitySkuList = activityRuleVo.getActivitySkuList();
        for (ActivitySku activitySku:activitySkuList) {
            activitySku.setActivityId(activityId);
            activitySkuMapper.insert(activitySku);
        }
    }
    //3 根据关键字查询匹配sku信息
    @Override
    public List<SkuInfo> findSkuInfoByKeyword(String keyword) {
        //第一步 根据关键字查询sku匹配内容列表
        // (1) service-product模块创建接口 据关键字查询sku匹配内容列表
        // (2) service-activity远程调用得到sku内容列表
        List<SkuInfo> skuInfoList =productFeignClient.findSkuInfoByKeyword(keyword);
        //判断：如果根据关键字查询不到匹配内容，直接返回空集合
        if(skuInfoList==null) {
            return null;
        }
        //从skuInfoList集合获取所有skuId
        List<Long> skuIdList =
                skuInfoList.stream().map(SkuInfo::getId).collect(Collectors.toList());

        //第二步 判断添加商品之前是否参加过活动，
        // 如果之前参加过，活动正在进行中，排除商品
        //// (1) 查询两张表判断 activity_info 和 activity_sku，编写SQL语句实现
        List<Long> existSkuIdList = baseMapper.selectSkuIdListExist(skuIdList);

        //// (2) 判断逻辑处理:排除已经参加活动商品
        List<SkuInfo> findSkuList = new ArrayList<>();
        //遍历全部sku列表
        for (SkuInfo skuInfo:skuInfoList) {
            if(!existSkuIdList.contains(skuInfo.getId())) {
                findSkuList.add(skuInfo);
            }
        }
        return findSkuList;
    }

    //查询商品获取规则数据
    @Override
    public List<ActivityRule> findActivityRule(Long skuId) {
        List<ActivityRule> activityRuleList = baseMapper.selectActivityRuleList(skuId);
        if(!CollectionUtils.isEmpty(activityRuleList)) {
            for(ActivityRule activityRule : activityRuleList) {
                activityRule.setRuleDesc(this.getRuleDesc(activityRule));
            }
        }
        return activityRuleList;

    }

    private String getRuleDesc(ActivityRule activityRule) {
        ActivityType activityType = activityRule.getActivityType();
        StringBuffer ruleDesc = new StringBuffer();
        if (activityType == ActivityType.FULL_REDUCTION) {
            ruleDesc
                    .append("满")
                    .append(activityRule.getConditionAmount())
                    .append("元减")
                    .append(activityRule.getBenefitAmount())
                    .append("元");
        } else {
            ruleDesc
                    .append("满")
                    .append(activityRule.getConditionNum())
                    .append("元打")
                    .append(activityRule.getBenefitDiscount())
                    .append("折");
        }
        return ruleDesc.toString();
    }

}
package com.example.userserver.common.task;

//@Component
//@Configuration
//@EnableScheduling
//@Slf4j
//public class TestTask2 implements SchedulingConfigurer {


//    @Mapper
//    public interface CronMapper{
//        @Select("select cron_expresion from cron where id=#{id}")
//        public String getCronById(String id);
//    }
//
//    @Autowired(required=true)
//    CronMapper cronMapper;
//
//
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
//        scheduledTaskRegistrar.addTriggerTask(
//                ()->log.info("执行动态定时任务"), //定时任务执行的内容
//                triggerContext -> { // 定时任务执行的时间  从数据库动态获取
//                    String cron = cronMapper.getCronById("excute_per_second");
//                    return  new CronTrigger(cron).nextExecutionTime(triggerContext);
//                }
//        );
//    }
//}

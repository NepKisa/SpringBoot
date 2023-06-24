```perl
#启动应用时修改以下两项
#收件人邮箱
spring.mail.receiver=1780161379@qq.com
#需要发送的附件目录
spring.mail.attachement=D:/Code/Project/Java/SpringBoot/application/src/main/resources/attachment/ide

未发送成功，断点会在附件目录的breakpoint.log文件里记录，再次启动应用时，
会根据breakpoint.log记录的文件的下一个文件开始发送

#需要全部重新发送时需要设置
spring.mail.resend=true
```
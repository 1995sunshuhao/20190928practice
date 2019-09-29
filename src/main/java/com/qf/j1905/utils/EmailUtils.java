package com.qf.j1905.utils;

import com.qf.j1905.dao.UserCodeMapper;
import com.qf.j1905.domain.UserCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class EmailUtils {
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender javamailSender;

    @Autowired
    private UserCodeMapper userCodeMapper;

    public String sendMail(String sendTo){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("注册验证码");
        String code = randomCode();
        simpleMailMessage.setText(code);
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(sendTo);
        try{
            javamailSender.send(simpleMailMessage);
            UserCode usercode = new UserCode();
            usercode.setUserCode(code);
            usercode.setUserMail(sendTo);
            usercode.setCodeStatus(1);
            List<UserCode> userMails = userCodeMapper.findByUserMail(sendTo);
            if (userMails.isEmpty()){
                userCodeMapper.save(usercode);
            }else {
                userCodeMapper.update(usercode);
            }
            return code;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }

    }

    public static String randomCode(){
        Random random = new Random();
        StringBuffer str = new StringBuffer();

        for (int i =0 ;i< 6;i++){
            int i1 = random.nextInt(9);
            str.append(i1);
        }
        return str.toString();
    }
}

package digitalhands.az.consultation_app.email;

import digitalhands.az.consultation_app.model.entity.Information;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(Information info) {
        try {
            MimeMessage mimeMessage1 = javaMailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper1 = new MimeMessageHelper(mimeMessage1, true);

            mimeMessageHelper1.setFrom(fromEmail);
            mimeMessageHelper1.setTo(info.getEmail());
            mimeMessageHelper1.setSubject("Consultation registration");
            mimeMessageHelper1.setText("Dear " + info.getFirstName() + ", your consultation registration has been completed successfully.");
            javaMailSender.send(mimeMessage1);

            MimeMessage mimeMessage2 = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper2 = new MimeMessageHelper(mimeMessage2, true);

            mimeMessageHelper2.setFrom(fromEmail);
            mimeMessageHelper2.setTo(fromEmail);
            mimeMessageHelper2.setSubject("New Consultation registration!");
            mimeMessageHelper2.setText(
                    String.format("""
                        New Consultation Notification!
                        Ad: %s,
                        Yash: %s,
                        Nomre: %s,
                        Email: %s,
                        Sahe: %s,
                        Alt sahe: %s,
                        Niye biz? %s,
                        Elaqe nece? %s,
                        Elaqe zaman? %s,
                        Serh & irad? %s
                    """,
                            info.getFirstName(),
                            info.getAge(),
                            info.getPhone(),
                            info.getEmail(),
                            info.getField(),
                            info.getFieldSpec(),
                            info.getWhyUs(),
                            info.getHowContact(),
                            info.getDateContact(),
                            info.getFeedback()
                    )
            );

            javaMailSender.send(mimeMessage2);

        } catch (Exception e) {
            throw new RuntimeException("Failed to send mail: " + e.getMessage(), e);
        }
    }
}

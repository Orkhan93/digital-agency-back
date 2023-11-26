package digitalhands.az.consultation_app.service;

import digitalhands.az.consultation_app.email.EmailService;
import digitalhands.az.consultation_app.model.entity.Information;
import digitalhands.az.consultation_app.repository.InformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InformationService {

    private final InformationRepository informationRepository;
    private final EmailService emailService;

    public void createConsultation(Information info) {
        emailService.sendMail(info);
        informationRepository.save(info);
    }
}

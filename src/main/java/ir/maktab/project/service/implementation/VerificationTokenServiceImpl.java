package ir.maktab.project.service.implementation;

import ir.maktab.project.data.dto.VerificationTokenDto;
import ir.maktab.project.data.dto.mapper.VerificationTokenMapper;
import ir.maktab.project.data.entity.members.VerificationToken;
import ir.maktab.project.data.repository.VerificationTokenRepository;
import ir.maktab.project.exception.HomeServiceException;
import ir.maktab.project.service.VerificationTokenService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

/**
 * @author Negin Mousavi
 */
@RequiredArgsConstructor
@Service
@Getter
public class VerificationTokenServiceImpl implements VerificationTokenService {
    private final VerificationTokenRepository verificationTokenRepository;

    @Override
    public void save(VerificationTokenDto myToken) {
        myToken.setExpireDate(calculateExpireDate(60));
        verificationTokenRepository.save(VerificationTokenMapper.mapVerificationTokenDtoToVerificationTokenForSaving(myToken));
    }

    @Override
    public VerificationTokenDto findByToken(String verificationToken) {
        Optional<VerificationToken> optionalVerificationToken = verificationTokenRepository.findByToken(verificationToken);
        if (optionalVerificationToken.isEmpty())
            throw new HomeServiceException("no token!");
        return VerificationTokenMapper.mapVerificationTokenToVerificationTokenDto(optionalVerificationToken.get());
    }

    @Override
    public void hasUsedToken(VerificationTokenDto verificationToken) {
        verificationToken.setUsedCount(1);
        verificationTokenRepository.save(VerificationTokenMapper.mapVerificationTokenDtoToVerificationToken(verificationToken));
    }

    public Date calculateExpireDate(int expireTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expireTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
}

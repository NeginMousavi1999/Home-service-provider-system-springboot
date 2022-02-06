package ir.maktab.project.service;

import ir.maktab.project.data.dto.VerificationTokenDto;

/**
 * @author Negin Mousavi
 */
public interface VerificationTokenService {
    void save(VerificationTokenDto myToken);

    VerificationTokenDto findByToken(String verificationToken);

    void hasUsedToken(VerificationTokenDto verificationToken);
}

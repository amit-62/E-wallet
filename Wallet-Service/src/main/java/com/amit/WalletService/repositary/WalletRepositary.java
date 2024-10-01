package com.amit.WalletService.repositary;

import com.amit.WalletService.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepositary extends JpaRepository<Wallet, Long> {
}

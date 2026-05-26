package com.parfum.config;

import com.parfum.model.Perfume;
import com.parfum.model.User;
import com.parfum.repository.PerfumeRepository;
import com.parfum.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.math.BigDecimal;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(PerfumeRepository perfumeRepo,
                               UserRepository userRepo,
                               PasswordEncoder encoder) {
        return args -> {
            // Admin
            if (!userRepo.existsByEmail("admin@flex.com")) {
                User admin = new User();
                admin.setName("Admin Parfum");
                admin.setEmail("admin@flex.com");
                admin.setPassword(encoder.encode("admin123"));
                admin.setRole(User.Role.ADMIN);
                userRepo.save(admin);
            }

            // Perfumes de exemplo
            String[][] perfumes = {
                    {"Chanel No. 5", "Chanel", "feminino", "299.90", "50",
                            "O icônico floral aldeído, símbolo atemporal de elegância feminina.",
                            "https://images.unsplash.com/photo-1541643600914-78b084683702?w=400", "4.9"},
                    {"Bleu de Chanel", "Chanel", "masculino", "349.90", "100",
                            "Fresco e amadeirado, ideal para o homem moderno e confiante.",
                            "https://images.unsplash.com/photo-1523293182086-7651a899d37f?w=400", "4.8"},
                    {"Sauvage", "Dior", "masculino", "319.90", "100",
                            "Selvagem e sedutor, com notas de pimenta e ambrox.",
                            "https://images.unsplash.com/photo-1594035910387-fea47794261f?w=400", "4.9"},
                    {"Miss Dior", "Dior", "feminino", "289.90", "50",
                            "Delicado bouquet de rosas com um toque moderno e vibrante.",
                            "https://images.unsplash.com/photo-1588514491024-01571e6e7d7d?w=400", "4.7"},
                    {"Black Orchid", "Tom Ford", "unissex", "589.90", "50",
                            "Luxuoso e misterioso, com orquídea negra e especiarias.",
                            "https://images.unsplash.com/photo-1590156206657-aec5e6e68b80?w=400", "4.8"},
                    {"Libre", "Yves Saint Laurent", "feminino", "279.90", "50",
                            "A essência da liberdade feminina: lavanda, manteiga de laranja e cedro.",
                            "https://images.unsplash.com/photo-1585386959984-a4155224a1ad?w=400", "4.6"},
                    {"Y Eau de Parfum", "Yves Saint Laurent", "masculino", "249.90", "60",
                            "Fresco, aromático e intenso — o aroma do homem ambicioso.",
                            "https://images.unsplash.com/photo-1611930022073-b7a4ba5fcccd?w=400", "4.5"},
                    {"Good Girl", "Carolina Herrera", "feminino", "259.90", "80",
                            "O jogo entre o bem e o mal: jasmim, cacau e baunilha.",
                            "https://images.unsplash.com/photo-1615634260167-c8cdede054de?w=400", "4.7"},
            };

            for (String[] p : perfumes) {
                Perfume perfume = new Perfume();
                perfume.setName(p[0]);
                perfume.setBrand(p[1]);
                perfume.setCategory(p[2]);
                perfume.setPrice(new BigDecimal(p[3]));
                perfume.setVolume(Integer.parseInt(p[4]));
                perfume.setDescription(p[5]);
                perfume.setImageUrl(p[6]);
                perfume.setRating(Double.parseDouble(p[7]));
                perfume.setStock(50);
                perfumeRepo.save(perfume);
            }

            System.out.println("✅ Dados iniciais carregados com sucesso!");
            System.out.println("📧 Admin: admin@flex.com | Senha: admin123");
        };
    }
}
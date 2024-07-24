package com.example.usersservice.service;

import com.example.usersservice.dto.UserDto;
import com.example.usersservice.entity.UserEntity;
import com.example.usersservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        userDto.setEncryptedPwd(passwordEncoder.encode(userDto.getPwd()));

        ModelMapper mapper = new ModelMapper();

        // 엄격한 매칭 전략, 불일치, 모호성이 발생하지 않는다
        // 주의 : 반드시 속성의 이름이 일치 해야 함.
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        UserEntity userEntity = mapper.map(userDto, UserEntity.class);
        userEntity.setEncryptedPwd("encrypted_password");
        userRepository.save(userEntity);

        return mapper.map(userEntity, UserDto.class);
    }
}

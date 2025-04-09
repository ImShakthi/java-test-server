package com.imshakthi.javatestserver.service;

import com.imshakthi.javatestserver.dto.UserInfo;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService implements UserDetailsService {

  private final PasswordEncoder encoder;

  private final Map<String, UserInfo> userDetails;

  public UserInfoService(final PasswordEncoder encoder) {
    this.encoder = encoder;
    userDetails = initUserDetails();
  }

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    final var userInfo = Optional.ofNullable(userDetails.get(username));

    return userInfo
        .map(UserInfoDetails::new)
        .orElseThrow(() -> new UsernameNotFoundException(username));
  }

  private Map<String, UserInfo> initUserDetails() {
    final var userDetails = new ConcurrentHashMap<String, UserInfo>();
    final String encodedPwd = encoder.encode("password");
    userDetails.put(
        "user", UserInfo.builder().name("user").password(encodedPwd).roles("USER").build());
    userDetails.put(
        "admin", UserInfo.builder().name("admin").password(encodedPwd).roles("ADMIN").build());

    return userDetails;
  }
}

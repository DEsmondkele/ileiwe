package com.ileiwe.security.user;

import com.ileiwe.data.model.Authority;
import com.ileiwe.data.model.LearningParty;
import com.ileiwe.data.repository.LearningPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LearningPartyUserServiceImpl implements UserDetailsService {
    @Autowired
    private LearningPartyRepository learningPartyRepository;


    private  List <SimpleGrantedAuthority> getAuthorities(List<Authority>authorities){
        return  authorities.stream().map(authority ->
                new SimpleGrantedAuthority(authority.getAuthority().name())).collect(Collectors.toList());
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LearningParty user =
        learningPartyRepository.findByEmail(email);
        if(user == null){
            throw  new UsernameNotFoundException("user with this email does not exist");
        }

        return new User(user.getEmail(), user.getPassWord(),getAuthorities(user.getAuthorities()));
    }
//    private  List<SimpleGrantedAuthority>getAuthorities(List<Authority>authorities){
//        return  authorities.stream().map(authority -> {
//            return new SimpleGrantedAuthority(authority.getAuthority().name());
//        }).collect(Collectors.toList());
   // }
}

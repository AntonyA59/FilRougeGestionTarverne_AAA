package aaa.tavern.service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class RandomService {
    
    Random random= new Random();
    
    public int getRandom(int max){
        return random.nextInt(max);
    }
}

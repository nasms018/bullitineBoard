package www.dream.bbs.party.mapper;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import www.dream.bbs.party.model.AccountabilityVO;
import www.dream.bbs.party.model.ContactPointVO;
import www.dream.bbs.party.model.OrganizationVO;
import www.dream.bbs.party.model.PersonVO;

@ExtendWith(SpringExtension.class)
@MybatisTest
//실 데이터베이스에 연결 시 필요한 어노테이션
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PartyMapperTest {
    @Autowired
    private PartyMapper mapper;
    
    //@Test
    @DisplayName("CreateOrganization Test")
    @Rollback(false)
    public void testCreateOrganization() {
    	try {
    		PasswordEncoder pwdEnc = new BCryptPasswordEncoder();
    		String pwd = pwdEnc.encode("dream");

    		List<ContactPointVO> listContactPoint = new ArrayList<>();
    		listContactPoint.add(new ContactPointVO("hand phone number", "010-0098-0999"));
    		listContactPoint.add(new ContactPointVO("home address", "서울 은평 녹번 000길"));
    				
    		OrganizationVO dream = new OrganizationVO("Dream Company", "dream", pwd, listContactPoint);
    		int cnt = mapper.createOrganization(dream);
    		System.out.println(dream.getId());
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    //@Test
    @DisplayName("CreateManager Test")
    @Rollback(false)
    public void testCreateManager() {
    	try {
    		PasswordEncoder pwdEnc = new BCryptPasswordEncoder();
    		String pwd = pwdEnc.encode("root");

    		List<ContactPointVO> listContactPoint = new ArrayList<>();
    		listContactPoint.add(new ContactPointVO("hand phone number", "010-4498-0999"));
    		listContactPoint.add(new ContactPointVO("home address", "서울 은평 녹번 440길"));
    				
    		PersonVO root = new PersonVO("김길동", "root", pwd, listContactPoint, true);
    		int cnt = mapper.createPerson(root);
    		mapper.createAccountability(new AccountabilityVO("manager", "0000", root.getId()));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    @Test
    @DisplayName("CreateMember Test")
    @Rollback(false)
    public void testCreateMember() {
    	try {
    		PasswordEncoder pwdEnc = new BCryptPasswordEncoder();
    		String pwd = pwdEnc.encode("cha");

    		List<ContactPointVO> listContactPoint = new ArrayList<>();
    		listContactPoint.add(new ContactPointVO("hand phone number", "010-7778-0999"));
    		listContactPoint.add(new ContactPointVO("home address", "서울 영등포 녹번 440길"));
    				
    		PersonVO lee = new PersonVO("차길동", "cha", pwd, listContactPoint, true);
    		int cnt = mapper.createPerson(lee);
    		mapper.createAccountability(new AccountabilityVO("member", "0000", lee.getId()));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}

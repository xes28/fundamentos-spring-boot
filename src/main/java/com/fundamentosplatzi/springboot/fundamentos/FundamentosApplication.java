package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.*;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import com.fundamentosplatzi.springboot.fundamentos.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private final Log logger = LogFactory.getLog(FundamentosApplication.class);

	private final ComponentDependency componentDependency;
	private final MyBean myBean;
	private final MyBeanWithDependency myBeanWithDependency;
	private final MyMathFuncPrintBean myMathFuncPrintBean;
	private final MyBeanWithProperties myBeanWithProperties;
	private final UserPojo userPojo;
	private final UserRepository userRepository;
	private final UserService userService;

	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency,
								  MyBean myBean,
								  MyBeanWithDependency myBeanWithDependency,
								  MyMathFuncPrintBean myMathFuncPrintBean,
								  MyBeanWithProperties myBeanWithProperties,
								  UserPojo userPojo,
								  UserRepository userRepository,
								  UserService userService){
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myMathFuncPrintBean = myMathFuncPrintBean;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//ejemplosAnteriores();
		saveUsersInDataBase();
		//getInformationJpqlFromUser();
		saveWithErrorTransactional();
	}

	private void saveUsersInDataBase(){
		User user1 = new User("Xavier", "xes@domain.com", LocalDate.of(1992,6,20));
		User user2 = new User("Mireia", "mta@domain.com", LocalDate.of(1998,4,17));
		User user3 = new User("user3", "user3@domain.com", LocalDate.of(1999,3,1));
		User user4 = new User("user4", "user4@domain.com", LocalDate.of(1995,2,23));
		User user5 = new User("user5", "user5@domain.com", LocalDate.of(2003,1,12));
		User user6 = new User("user6", "user6@domain.com", LocalDate.of(2000,8,13));
		User user7 = new User("user7", "user7@domain.com", LocalDate.of(1989,9,25));
		User user8 = new User("user8", "user8@domain.com", LocalDate.of(1985,10,28));
		User user9 = new User("user9", "user9@domain.com", LocalDate.of(1980,11,5));
		User user10 = new User("user10", "user10@domain.com", LocalDate.of(1993,11,8));
		User user11 = new User("user11", "user11@domain.com", LocalDate.of(1994,12,11));
		User user12 = new User("user12", "user12@domain.com", LocalDate.of(1995,3,30));
		List<User> users = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11, user12);
		users.stream().forEach(userRepository::save);

	}

	private void getInformationJpqlFromUser(){
		logger.info("Usuario con el método findByUserEmail: " + userRepository.findByUserEmail("mta@domain.com")
				.orElseThrow(() -> new RuntimeException("No se encontró al usuario")));

		userRepository.findAndSort("user", Sort.by("id").descending())
				.stream()
				.forEach(user -> logger.info("Usuario con método sort: " + user));

		userRepository.findByName("Xavier")
				.stream()
				.forEach(user -> logger.info("Usuario con query method " + user));

		logger.info("Usuario con query method findByEmailAndName " + userRepository.findByEmailAndName("user12@domain.com", "user12")
				.orElseThrow(() -> new RuntimeException("No se encontró al usuario")));

		userRepository.findByNameLike("%user%")
				.stream()
				.forEach(user -> logger.info(("Usuario con query method findByNameLike " + user)));

		userRepository.findByNameOrEmail("user5", null)
				.stream()
				.forEach(user -> logger.info(("Usuario con query method findByNameOrEmail " + user)));

		userRepository.findByBirthDateBetween(LocalDate.of(1995, 1, 1), LocalDate.of(2003, 1, 1))
				.stream()
				.forEach(user -> logger.info("Usuario con intervalo de fecha " + user));

		userRepository.findByNameLikeOrderByIdDesc("%u%")
				.stream()
				.forEach(user -> logger.info("Usuario con order por id descendiente " + user));

		userRepository.findByNameContainingOrderByIdAsc("av")
				.stream()
				.forEach(user -> logger.info("Usuario que contenga cadena por id ascendente " + user));

		logger.info("El usuario a partir del named parameter es " + userRepository.getAllByBirthdayAndEmail(LocalDate.of(1992, 6, 20), "xes@domain.com")
				.orElseThrow(() -> new RuntimeException("No se encontro el usuario a partir del named parametrer")));
	}

	private void saveWithErrorTransactional(){
		User test1 = new User("TestTransactional1", "TestTransactional1@domain.com", LocalDate.now());
		User test2 = new User("TestTransactional2", "TestTransactional2@domain.com", LocalDate.now());
		User test3 = new User("TestTransactional3", "TestTransactional1@domain.com", LocalDate.now());
		User test4 = new User("TestTransactional4", "TestTransactional4@domain.com", LocalDate.now());

		List<User> users = Arrays.asList(test1, test2, test3, test4);

		try{
			userService.saveTransactional(users);
		}catch (Exception e){
			logger.error("Esta es una excepción dentro del método transaccional " + e);
		}

		userService.getAllUsers()
				.stream()
				.forEach(user -> logger.info("Este es el usuario dentro del método transaccional " + user));

	}

	private void ejemplosAnteriores(){
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		myMathFuncPrintBean.printMathFunc(4,0);
		System.out.println(myBeanWithProperties.function());
		System.out.println("Email: "+userPojo.getEmail() + "\nPassword: "+ userPojo.getPassword() + "\nAge: " + userPojo.getAge());
		try{
			int value = 10/0;
			logger.debug("Mi valor:"+value);
		}catch (Exception e){
			logger.error("Esto es un error al dividir por cero " + e.getMessage());
		}
	}
}

package dev.zergling.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder  // builder pattern
@NoArgsConstructor  //매개변수 없는 생성자 구
@AllArgsConstructor  // 모든 맴버 변수를 매개변수로 받는 생성자를 구
@Data
@Entity
@Table(name = "Todo")
public class TodoEntity {
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	private String userId;
	private String title;
	private boolean done;
	

}

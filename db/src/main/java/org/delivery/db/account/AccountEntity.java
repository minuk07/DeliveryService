package org.delivery.db.account;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;

@SuperBuilder //부모로부터 상속받은 변수도 포함
@Data
@EqualsAndHashCode(callSuper = true) //부모에게 있는 값까지 포함해서 객체 비교
@Entity
@Table(name = "account")
public class AccountEntity extends BaseEntity {
}

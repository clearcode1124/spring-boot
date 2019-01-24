package com.wade.demo.domain.entity;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import lombok.Data;

@Data
@Table("user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	@Column("open_id")
	private String openId;

	@Column("username")
	private String username;
	@Column("password")
	private String password;

	@Column("salt")
	private String salt;// 不应该由用户输入，而是在保存时生成随机

	@Column("nickname")
	private String nickname;

	@Column("phone")
	private String phone;

	@Column("email")
	private String email;

	@Column("password_reseted_at")
	@Comment("密码最后一次重置时间")
	private Long passwordResetedAt;

	@Column("password_updated_at")
	@Comment("密码最后一次更新时间")
	private Long passwordUpdatedAt;

	@Column("locked")
	@Comment("是否锁定")
	private Boolean locked;

	@Column("super_admin")
	@Comment("是否为超级管理员")
	private Boolean superAdmin;

	/**
	 * 子系统id
	 */
	private Long systemId;

	private String roleName;

	public boolean isPasswordReseted() {
		if (passwordResetedAt == null) {
			return false;
		}
		return (passwordUpdatedAt == null || passwordUpdatedAt.compareTo(passwordResetedAt) < 0);
	}

	/**
	 * 返回不包含password和salt这类敏感信息的用户信息副本
	 * 
	 * @return
	 */
	public User insensitiveOne() {
		User userDetail = new User();
		userDetail.setId(getId());
		userDetail.setUsername(getUsername());
		userDetail.setNickname(getNickname());
		userDetail.setEmail(getEmail());
		userDetail.setPhone(getPhone());
		userDetail.setSuperAdmin(getSuperAdmin());
		userDetail.setPasswordResetedAt(getPasswordResetedAt());
		userDetail.setPasswordUpdatedAt(getPasswordUpdatedAt());
		return userDetail;
	}

	public static class SqlKey {
		/**
		 * 分页获取包括自己创建的以及与自己有关联的用户
		 */
		public static final String LIST_MY_USER = "list.my.user";

		/**
		 * 分页获取用户列表（附带roleName字段）
		 */
		public static final String LIST_WITH_ROLE_NAME = "list.with.role.name";

		/**
		 * 获取当前用户下SUB1的所有用户
		 */
		public static final String LIST_SUB1_USERS = "list.sub1.users";

		/**
		 * 根据角色获取用户列表
		 */
		public static final String LIST_BY_ROLENAME = "list.by.rolename";

		private SqlKey() {
		}
	}

	public static final class Role {

		/**
		 * 超级管理员
		 */
		public static final String SA = "ROLE_SA";
		/**
		 * 匿名用户（游客） -大部分情况下是没有这个的
		 */
		public static final String ANONYMOUS = "ROLE_ANONYMOUS";

		private Role() {
		}
	}

	public static final class Message {
		public static final String INVALID_PW = "invalidPassword";
		public static final String INVALID_UP = "invalidUsernameOrPassword";
		public static final String LOCKED = "locked";
		public static final String PW_RESETED = "passwordReseted";

		private Message() {
		}
	}

	public static final class Field {
		public static final String ID = "id";
		public static final String OPENID = "openId";
		public static final String USERNAME = "username";
		public static final String SALT = "salt";
		public static final String NICKNAME = "nickname";
		public static final String PHONE = "phone";
		public static final String EMAIL = "email";
		public static final String LOCKED = "locked";
		public static final String SUPER_ADMIN = "superAdmin";

		private Field() {
		}
	}
}

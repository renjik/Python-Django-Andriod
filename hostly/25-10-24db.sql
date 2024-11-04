/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.9 : Database - hostly_django
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
USE `hostly_django`;

/*Table structure for table `auth_group` */

DROP TABLE IF EXISTS `auth_group`;

CREATE TABLE `auth_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `auth_group` */

/*Table structure for table `auth_group_permissions` */

DROP TABLE IF EXISTS `auth_group_permissions`;

CREATE TABLE `auth_group_permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_group_permissions_group_id_permission_id_0cd325b0_uniq` (`group_id`,`permission_id`),
  KEY `auth_group_permissions_group_id_b120cbf9` (`group_id`),
  KEY `auth_group_permissions_permission_id_84c5c92e` (`permission_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `auth_group_permissions` */

/*Table structure for table `auth_permission` */

DROP TABLE IF EXISTS `auth_permission`;

CREATE TABLE `auth_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `content_type_id` int(11) NOT NULL,
  `codename` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_permission_content_type_id_codename_01ab375a_uniq` (`content_type_id`,`codename`),
  KEY `auth_permission_content_type_id_2f476e4b` (`content_type_id`)
) ENGINE=MyISAM AUTO_INCREMENT=81 DEFAULT CHARSET=latin1;

/*Data for the table `auth_permission` */

insert  into `auth_permission`(`id`,`name`,`content_type_id`,`codename`) values 
(1,'Can add log entry',1,'add_logentry'),
(2,'Can change log entry',1,'change_logentry'),
(3,'Can delete log entry',1,'delete_logentry'),
(4,'Can view log entry',1,'view_logentry'),
(5,'Can add permission',2,'add_permission'),
(6,'Can change permission',2,'change_permission'),
(7,'Can delete permission',2,'delete_permission'),
(8,'Can view permission',2,'view_permission'),
(9,'Can add group',3,'add_group'),
(10,'Can change group',3,'change_group'),
(11,'Can delete group',3,'delete_group'),
(12,'Can view group',3,'view_group'),
(13,'Can add user',4,'add_user'),
(14,'Can change user',4,'change_user'),
(15,'Can delete user',4,'delete_user'),
(16,'Can view user',4,'view_user'),
(17,'Can add content type',5,'add_contenttype'),
(18,'Can change content type',5,'change_contenttype'),
(19,'Can delete content type',5,'delete_contenttype'),
(20,'Can view content type',5,'view_contenttype'),
(21,'Can add session',6,'add_session'),
(22,'Can change session',6,'change_session'),
(23,'Can delete session',6,'delete_session'),
(24,'Can view session',6,'view_session'),
(25,'Can add login',7,'add_login'),
(26,'Can change login',7,'change_login'),
(27,'Can delete login',7,'delete_login'),
(28,'Can view login',7,'view_login'),
(29,'Can add department',8,'add_department'),
(30,'Can change department',8,'change_department'),
(31,'Can delete department',8,'delete_department'),
(32,'Can view department',8,'view_department'),
(33,'Can add course',9,'add_course'),
(34,'Can change course',9,'change_course'),
(35,'Can delete course',9,'delete_course'),
(36,'Can view course',9,'view_course'),
(37,'Can add student',10,'add_student'),
(38,'Can change student',10,'change_student'),
(39,'Can delete student',10,'delete_student'),
(40,'Can view student',10,'view_student'),
(41,'Can add room',11,'add_room'),
(42,'Can change room',11,'change_room'),
(43,'Can delete room',11,'delete_room'),
(44,'Can view room',11,'view_room'),
(45,'Can add room allocation',12,'add_roomallocation'),
(46,'Can change room allocation',12,'change_roomallocation'),
(47,'Can delete room allocation',12,'delete_roomallocation'),
(48,'Can view room allocation',12,'view_roomallocation'),
(49,'Can add warden',13,'add_warden'),
(50,'Can change warden',13,'change_warden'),
(51,'Can delete warden',13,'delete_warden'),
(52,'Can view warden',13,'view_warden'),
(53,'Can add attendance',14,'add_attendance'),
(54,'Can change attendance',14,'change_attendance'),
(55,'Can delete attendance',14,'delete_attendance'),
(56,'Can view attendance',14,'view_attendance'),
(57,'Can add notification',15,'add_notification'),
(58,'Can change notification',15,'change_notification'),
(59,'Can delete notification',15,'delete_notification'),
(60,'Can view notification',15,'view_notification'),
(61,'Can add complaint',16,'add_complaint'),
(62,'Can change complaint',16,'change_complaint'),
(63,'Can delete complaint',16,'delete_complaint'),
(64,'Can view complaint',16,'view_complaint'),
(65,'Can add payment',17,'add_payment'),
(66,'Can change payment',17,'change_payment'),
(67,'Can delete payment',17,'delete_payment'),
(68,'Can view payment',17,'view_payment'),
(69,'Can add maintenance',18,'add_maintenance'),
(70,'Can change maintenance',18,'change_maintenance'),
(71,'Can delete maintenance',18,'delete_maintenance'),
(72,'Can view maintenance',18,'view_maintenance'),
(73,'Can add check in check out',19,'add_checkincheckout'),
(74,'Can change check in check out',19,'change_checkincheckout'),
(75,'Can delete check in check out',19,'delete_checkincheckout'),
(76,'Can view check in check out',19,'view_checkincheckout'),
(77,'Can add add on time request',20,'add_addontimerequest'),
(78,'Can change add on time request',20,'change_addontimerequest'),
(79,'Can delete add on time request',20,'delete_addontimerequest'),
(80,'Can view add on time request',20,'view_addontimerequest');

/*Table structure for table `auth_user` */

DROP TABLE IF EXISTS `auth_user`;

CREATE TABLE `auth_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(128) NOT NULL,
  `last_login` datetime(6) DEFAULT NULL,
  `is_superuser` tinyint(1) NOT NULL,
  `username` varchar(150) NOT NULL,
  `first_name` varchar(150) NOT NULL,
  `last_name` varchar(150) NOT NULL,
  `email` varchar(254) NOT NULL,
  `is_staff` tinyint(1) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `date_joined` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `auth_user` */

/*Table structure for table `auth_user_groups` */

DROP TABLE IF EXISTS `auth_user_groups`;

CREATE TABLE `auth_user_groups` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_user_groups_user_id_group_id_94350c0c_uniq` (`user_id`,`group_id`),
  KEY `auth_user_groups_user_id_6a12ed8b` (`user_id`),
  KEY `auth_user_groups_group_id_97559544` (`group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `auth_user_groups` */

/*Table structure for table `auth_user_user_permissions` */

DROP TABLE IF EXISTS `auth_user_user_permissions`;

CREATE TABLE `auth_user_user_permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_user_user_permissions_user_id_permission_id_14a6b632_uniq` (`user_id`,`permission_id`),
  KEY `auth_user_user_permissions_user_id_a95ead1b` (`user_id`),
  KEY `auth_user_user_permissions_permission_id_1fbb5f2c` (`permission_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `auth_user_user_permissions` */

/*Table structure for table `django_admin_log` */

DROP TABLE IF EXISTS `django_admin_log`;

CREATE TABLE `django_admin_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `action_time` datetime(6) NOT NULL,
  `object_id` longtext,
  `object_repr` varchar(200) NOT NULL,
  `action_flag` smallint(5) unsigned NOT NULL,
  `change_message` longtext NOT NULL,
  `content_type_id` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `django_admin_log_content_type_id_c4bce8eb` (`content_type_id`),
  KEY `django_admin_log_user_id_c564eba6` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `django_admin_log` */

/*Table structure for table `django_content_type` */

DROP TABLE IF EXISTS `django_content_type`;

CREATE TABLE `django_content_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_label` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `django_content_type_app_label_model_76bd3d3b_uniq` (`app_label`,`model`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

/*Data for the table `django_content_type` */

insert  into `django_content_type`(`id`,`app_label`,`model`) values 
(1,'admin','logentry'),
(2,'auth','permission'),
(3,'auth','group'),
(4,'auth','user'),
(5,'contenttypes','contenttype'),
(6,'sessions','session'),
(7,'hostlyapp','login'),
(8,'hostlyapp','department'),
(9,'hostlyapp','course'),
(10,'hostlyapp','student'),
(11,'hostlyapp','room'),
(12,'hostlyapp','roomallocation'),
(13,'hostlyapp','warden'),
(14,'hostlyapp','attendance'),
(15,'hostlyapp','notification'),
(16,'hostlyapp','complaint'),
(17,'hostlyapp','payment'),
(18,'hostlyapp','maintenance'),
(19,'hostlyapp','checkincheckout'),
(20,'hostlyapp','addontimerequest');

/*Table structure for table `django_migrations` */

DROP TABLE IF EXISTS `django_migrations`;

CREATE TABLE `django_migrations` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `applied` datetime(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=45 DEFAULT CHARSET=latin1;

/*Data for the table `django_migrations` */

insert  into `django_migrations`(`id`,`app`,`name`,`applied`) values 
(1,'contenttypes','0001_initial','2024-10-16 06:19:23.863927'),
(2,'auth','0001_initial','2024-10-16 06:19:24.193438'),
(3,'admin','0001_initial','2024-10-16 06:19:24.263963'),
(4,'admin','0002_logentry_remove_auto_add','2024-10-16 06:19:24.275495'),
(5,'admin','0003_logentry_add_action_flag_choices','2024-10-16 06:19:24.281755'),
(6,'contenttypes','0002_remove_content_type_name','2024-10-16 06:19:24.313037'),
(7,'auth','0002_alter_permission_name_max_length','2024-10-16 06:19:24.332626'),
(8,'auth','0003_alter_user_email_max_length','2024-10-16 06:19:24.348128'),
(9,'auth','0004_alter_user_username_opts','2024-10-16 06:19:24.357098'),
(10,'auth','0005_alter_user_last_login_null','2024-10-16 06:19:24.372563'),
(11,'auth','0006_require_contenttypes_0002','2024-10-16 06:19:24.375714'),
(12,'auth','0007_alter_validators_add_error_messages','2024-10-16 06:19:24.387195'),
(13,'auth','0008_alter_user_username_max_length','2024-10-16 06:19:24.407232'),
(14,'auth','0009_alter_user_last_name_max_length','2024-10-16 06:19:24.425964'),
(15,'auth','0010_alter_group_name_max_length','2024-10-16 06:19:24.449693'),
(16,'auth','0011_update_proxy_permissions','2024-10-16 06:19:24.461701'),
(17,'auth','0012_alter_user_first_name_max_length','2024-10-16 06:19:24.480325'),
(18,'hostlyapp','0001_initial','2024-10-16 06:19:24.515039'),
(19,'hostlyapp','0002_department','2024-10-16 06:19:24.541665'),
(20,'hostlyapp','0003_course','2024-10-16 06:19:24.585314'),
(21,'hostlyapp','0004_student','2024-10-16 06:19:24.640907'),
(22,'hostlyapp','0005_room_roomallocation','2024-10-16 06:19:24.697347'),
(23,'hostlyapp','0006_rename_course_student_course','2024-10-16 06:19:24.706891'),
(24,'hostlyapp','0007_alter_student_dob','2024-10-16 06:19:24.722280'),
(25,'hostlyapp','0008_alter_student_parents_phone_and_more','2024-10-16 06:19:24.768181'),
(26,'hostlyapp','0009_warden','2024-10-16 06:19:24.806547'),
(27,'hostlyapp','0010_attendance','2024-10-16 06:19:24.842957'),
(28,'hostlyapp','0011_notification','2024-10-16 06:19:24.856975'),
(29,'hostlyapp','0012_complaint','2024-10-16 06:19:24.896493'),
(30,'hostlyapp','0013_payment','2024-10-16 06:19:24.930146'),
(31,'hostlyapp','0014_rename_room_roomallocation_room','2024-10-16 06:19:24.948663'),
(32,'hostlyapp','0015_alter_roomallocation_allocate_date','2024-10-16 06:19:24.963889'),
(33,'hostlyapp','0016_maintenance','2024-10-16 06:19:25.005524'),
(34,'hostlyapp','0017_checkincheckout','2024-10-16 06:19:25.044559'),
(35,'hostlyapp','0018_addontimerequest','2024-10-16 06:19:25.081005'),
(36,'sessions','0001_initial','2024-10-16 06:19:25.130146'),
(37,'hostlyapp','0019_alter_room_capacity','2024-10-18 08:25:38.186328'),
(38,'hostlyapp','0020_auto_20241019_1556','2024-10-19 10:26:47.939348'),
(39,'hostlyapp','0021_auto_20241021_1508','2024-10-21 09:38:34.625775'),
(40,'hostlyapp','0022_auto_20241021_1611','2024-10-21 10:41:52.802241'),
(41,'hostlyapp','0023_remove_payment_month_of_rent','2024-10-22 09:11:58.316165'),
(42,'hostlyapp','0024_auto_20241022_1951','2024-10-22 14:21:18.197662'),
(43,'hostlyapp','0025_auto_20241022_2037','2024-10-22 15:07:54.675991'),
(44,'hostlyapp','0026_maintenance_description','2024-10-23 08:50:09.955573');

/*Table structure for table `django_session` */

DROP TABLE IF EXISTS `django_session`;

CREATE TABLE `django_session` (
  `session_key` varchar(40) NOT NULL,
  `session_data` longtext NOT NULL,
  `expire_date` datetime(6) NOT NULL,
  PRIMARY KEY (`session_key`),
  KEY `django_session_expire_date_a5c62663` (`expire_date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `django_session` */

insert  into `django_session`(`session_key`,`session_data`,`expire_date`) values 
('jep52ghxjcwqwbxfxlugb7r8eghfgbo2','eyJsaWQiOjF9:1t0xU1:qQNOF3fLX-3ZXcoKajLOBcBvVDa0Y10Yr0e3pP3Shco','2024-10-30 06:26:09.591012'),
('cxx1e4gbbqgeiuitktbc22wxu52shoxe','eyJsaWQiOjF9:1t3XhC:x9mlbKvBlfRjMOiYfQ6l3EHFXkRVpEciIpvGIXNl2GU','2024-11-06 09:30:26.397877'),
('9ftd04avze24ollrkfnae8iyssobguo1','eyJsaWQiOjF9:1t43Aw:3J-nrTZG62P8pZDwsNki4cAxYh6LOYQ6Ln249cn7MXg','2024-11-07 19:07:14.096584');

/*Table structure for table `hostlyapp_addontimerequest` */

DROP TABLE IF EXISTS `hostlyapp_addontimerequest`;

CREATE TABLE `hostlyapp_addontimerequest` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `estimated_time` varchar(100) NOT NULL,
  `reason` varchar(100) NOT NULL,
  `status` varchar(100) NOT NULL,
  `CHECKINCHECKOUT_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `hostlyapp_addontimerequest_CHECKINCHECKOUT_id_c8a7fd3e` (`CHECKINCHECKOUT_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `hostlyapp_addontimerequest` */

insert  into `hostlyapp_addontimerequest`(`id`,`estimated_time`,`reason`,`status`,`CHECKINCHECKOUT_id`) values 
(1,'12:30','Reason','approved',1),
(2,'5:00','in hospital ','pending',3),
(3,'12:30','Reasonhxxhx','pending',3);

/*Table structure for table `hostlyapp_attendance` */

DROP TABLE IF EXISTS `hostlyapp_attendance`;

CREATE TABLE `hostlyapp_attendance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` varchar(100) NOT NULL,
  `status` varchar(100) NOT NULL,
  `STUDENT_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `hostlyapp_attendance_STUDENT_id_bc452154` (`STUDENT_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `hostlyapp_attendance` */

insert  into `hostlyapp_attendance`(`id`,`date`,`status`,`STUDENT_id`) values 
(1,'2024-10-25','Present',3);

/*Table structure for table `hostlyapp_checkincheckout` */

DROP TABLE IF EXISTS `hostlyapp_checkincheckout`;

CREATE TABLE `hostlyapp_checkincheckout` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `c_out_datetime` varchar(100) NOT NULL,
  `c_in_datetime` varchar(100) DEFAULT NULL,
  `status` varchar(100) NOT NULL,
  `STUDENT_id` bigint(20) NOT NULL,
  `date_time` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `hostlyapp_checkincheckout_STUDENT_id_de716edd` (`STUDENT_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `hostlyapp_checkincheckout` */

insert  into `hostlyapp_checkincheckout`(`id`,`c_out_datetime`,`c_in_datetime`,`status`,`STUDENT_id`,`date_time`) values 
(1,'2024-03-03 00:53:35','2024-03-03 00:53:45.446154','out',3,'2024-03-03'),
(2,'2024-10-23 15:05:09.128335','2024-10-23 09:49:23','out',2,'2024-10-23');

/*Table structure for table `hostlyapp_complaint` */

DROP TABLE IF EXISTS `hostlyapp_complaint`;

CREATE TABLE `hostlyapp_complaint` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(100) NOT NULL,
  `date` varchar(100) NOT NULL,
  `reply` varchar(100) DEFAULT NULL,
  `STUDENT_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `hostlyapp_complaint_STUDENT_id_7e536157` (`STUDENT_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `hostlyapp_complaint` */

insert  into `hostlyapp_complaint`(`id`,`description`,`date`,`reply`,`STUDENT_id`) values 
(1,'hhh','','pending',3),
(2,'heyeyy','','pending',3);

/*Table structure for table `hostlyapp_course` */

DROP TABLE IF EXISTS `hostlyapp_course`;

CREATE TABLE `hostlyapp_course` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(200) NOT NULL,
  `type` varchar(200) NOT NULL,
  `DEPARTMENT_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `hostlyapp_course_DEPARTMENT_id_62b7b2a9` (`DEPARTMENT_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `hostlyapp_course` */

insert  into `hostlyapp_course`(`id`,`course_name`,`type`,`DEPARTMENT_id`) values 
(1,'MCA','mm',1),
(2,'MSC','nn',1);

/*Table structure for table `hostlyapp_department` */

DROP TABLE IF EXISTS `hostlyapp_department`;

CREATE TABLE `hostlyapp_department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `department_name` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `hostlyapp_department` */

insert  into `hostlyapp_department`(`id`,`department_name`) values 
(1,'MSC');

/*Table structure for table `hostlyapp_login` */

DROP TABLE IF EXISTS `hostlyapp_login`;

CREATE TABLE `hostlyapp_login` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `usertype` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `hostlyapp_login` */

insert  into `hostlyapp_login`(`id`,`username`,`password`,`usertype`) values 
(1,'admin','1234','admin'),
(5,'w','123','warden'),
(3,'y','123','student'),
(4,'m','123','student'),
(6,'k','123','student');

/*Table structure for table `hostlyapp_maintenance` */

DROP TABLE IF EXISTS `hostlyapp_maintenance`;

CREATE TABLE `hostlyapp_maintenance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reported_date` varchar(100) NOT NULL,
  `student_status` varchar(100) NOT NULL,
  `STUDENT_id` bigint(20) NOT NULL,
  `ROOMALLOCATION_id` bigint(20) NOT NULL,
  `description` varchar(500) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `hostlyapp_maintenance_STUDENT_id_75be16f0` (`STUDENT_id`),
  KEY `hostlyapp_maintenance_ROOMALLOCATION_id_e7f4ac81` (`ROOMALLOCATION_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `hostlyapp_maintenance` */

insert  into `hostlyapp_maintenance`(`id`,`reported_date`,`student_status`,`STUDENT_id`,`ROOMALLOCATION_id`,`description`) values 
(1,'2024-10-23','requested',3,2,'ydyf'),
(2,'2024-10-23','requested',3,2,'fmfnxkf NC cnxm'),
(3,'2024-10-23','requested',3,2,'hccjjc');

/*Table structure for table `hostlyapp_notification` */

DROP TABLE IF EXISTS `hostlyapp_notification`;

CREATE TABLE `hostlyapp_notification` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_of_notification` varchar(100) NOT NULL,
  `description` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `hostlyapp_notification` */

insert  into `hostlyapp_notification`(`id`,`date_of_notification`,`description`) values 
(1,'2024-10-18','Notu'),
(2,'2024-10-22','Room No 1 dues to  pay Rs 2000');

/*Table structure for table `hostlyapp_payment` */

DROP TABLE IF EXISTS `hostlyapp_payment`;

CREATE TABLE `hostlyapp_payment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `payment_amount` varchar(100) NOT NULL,
  `date_of_payment` varchar(100) NOT NULL,
  `STUDENT_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `hostlyapp_payment_STUDENT_id_4f6fd798` (`STUDENT_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `hostlyapp_payment` */

insert  into `hostlyapp_payment`(`id`,`payment_amount`,`date_of_payment`,`STUDENT_id`) values 
(1,'2000','2024-10-22',3);

/*Table structure for table `hostlyapp_room` */

DROP TABLE IF EXISTS `hostlyapp_room`;

CREATE TABLE `hostlyapp_room` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `room_no` varchar(100) NOT NULL,
  `capacity` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `hostlyapp_room` */

insert  into `hostlyapp_room`(`id`,`room_no`,`capacity`) values 
(1,'1','Medium Room,4 peoples ,Attached Bathroom Rs 2000/-'),
(2,'2','Medium Room,4 peoples ,Attached Bathroom Rs 2000/-');

/*Table structure for table `hostlyapp_roomallocation` */

DROP TABLE IF EXISTS `hostlyapp_roomallocation`;

CREATE TABLE `hostlyapp_roomallocation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `allocate_date` varchar(200) NOT NULL,
  `status` varchar(200) NOT NULL,
  `STUDENT_id` bigint(20) NOT NULL,
  `ROOM_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `hostlyapp_roomallocation_STUDENT_id_892357c4` (`STUDENT_id`),
  KEY `hostlyapp_roomallocation_room_id_7c0d0415` (`ROOM_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `hostlyapp_roomallocation` */

insert  into `hostlyapp_roomallocation`(`id`,`allocate_date`,`status`,`STUDENT_id`,`ROOM_id`) values 
(1,'2024-10-19','Allocated',1,1),
(2,'2024-10-21','Allocated',3,1);

/*Table structure for table `hostlyapp_student` */

DROP TABLE IF EXISTS `hostlyapp_student`;

CREATE TABLE `hostlyapp_student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_name` varchar(200) NOT NULL,
  `gender` varchar(200) NOT NULL,
  `dob` varchar(200) NOT NULL,
  `house_name` varchar(100) NOT NULL,
  `post_office` varchar(100) NOT NULL,
  `city` varchar(50) NOT NULL,
  `district` varchar(50) NOT NULL,
  `state` varchar(50) NOT NULL,
  `pin_no` varchar(200) NOT NULL,
  `sem` varchar(200) NOT NULL,
  `phone_number` varchar(200) NOT NULL,
  `parents_phone` varchar(200) NOT NULL,
  `photo` varchar(200) NOT NULL,
  `LOGIN_id` bigint(20) NOT NULL,
  `COURSE_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `hostlyapp_student_LOGIN_id_b630c66c` (`LOGIN_id`),
  KEY `hostlyapp_student_course_id_26bdc74a` (`COURSE_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `hostlyapp_student` */

insert  into `hostlyapp_student`(`id`,`student_name`,`gender`,`dob`,`house_name`,`post_office`,`city`,`district`,`state`,`pin_no`,`sem`,`phone_number`,`parents_phone`,`photo`,`LOGIN_id`,`COURSE_id`) values 
(1,'james','male','14/10/2024','y','j','j','h','u','h','5th','h','u','file_avatar.jpg',3,1),
(2,'mari','male','13/10/2024','m','m','m','m','m','m','4th','m','m','file_avatar.jpg',4,2),
(3,'kelvin','male','21/10/2024','k','k','kochi','eraklum','k','234567','5th','6767676767','5656565656','1729235111375.png',6,2);

/*Table structure for table `hostlyapp_warden` */

DROP TABLE IF EXISTS `hostlyapp_warden`;

CREATE TABLE `hostlyapp_warden` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fname` varchar(100) NOT NULL,
  `lname` varchar(100) NOT NULL,
  `gender` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone_no` varchar(100) NOT NULL,
  `post_office` varchar(100) NOT NULL,
  `city` varchar(100) NOT NULL,
  `district` varchar(100) NOT NULL,
  `pin_no` varchar(100) NOT NULL,
  `photo` varchar(200) NOT NULL,
  `LOGIN_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `hostlyapp_warden_LOGIN_id_aaff2a10` (`LOGIN_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `hostlyapp_warden` */

insert  into `hostlyapp_warden`(`id`,`fname`,`lname`,`gender`,`email`,`phone_no`,`post_office`,`city`,`district`,`pin_no`,`photo`,`LOGIN_id`) values 
(1,'Edwin','tom','Male','renjik@gmial.com','0730603395','abc place, xyz street','Manjeri','Ernaklm','767662','/media/20241018-122505.jpg',5);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

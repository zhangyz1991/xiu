/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : xiu

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 31/12/2019 15:01:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for a
-- ----------------------------
DROP TABLE IF EXISTS `a`;
CREATE TABLE `a`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of a
-- ----------------------------
INSERT INTO `a` VALUES (1, 'AA');
INSERT INTO `a` VALUES (2, 'BBB');
INSERT INTO `a` VALUES (3, 'A1');
INSERT INTO `a` VALUES (4, 'A2');
INSERT INTO `a` VALUES (5, 'A3');

-- ----------------------------
-- Table structure for b
-- ----------------------------
DROP TABLE IF EXISTS `b`;
CREATE TABLE `b`  (
  `id` int(11) NOT NULL,
  `x_id` int(11) NOT NULL,
  `a_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of b
-- ----------------------------
INSERT INTO `b` VALUES (1, 1, 1);
INSERT INTO `b` VALUES (2, 1, 1);
INSERT INTO `b` VALUES (3, 1, 3);
INSERT INTO `b` VALUES (4, 1, 3);
INSERT INTO `b` VALUES (5, 1, 4);
INSERT INTO `b` VALUES (6, 1, 5);
INSERT INTO `b` VALUES (7, 1, 5);
INSERT INTO `b` VALUES (8, 1, 5);

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`  (
  `id` bigint(20) NOT NULL COMMENT '班级ID',
  `grade_id` bigint(20) NOT NULL COMMENT '年级ID',
  `grade_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '年级',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '班级',
  `size` int(11) NULL DEFAULT NULL COMMENT '班级人数',
  PRIMARY KEY (`id`, `grade_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES (1203923344928788481, 1201167167538995202, '2019年八年级', '2019年八三班', 79);

-- ----------------------------
-- Table structure for class_user
-- ----------------------------
DROP TABLE IF EXISTS `class_user`;
CREATE TABLE `class_user`  (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `grade_id` bigint(20) NULL DEFAULT NULL COMMENT '年级ID',
  `class_id` bigint(20) NULL DEFAULT NULL COMMENT '班级ID',
  `class_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '班级',
  `class_user_no` int(11) NULL DEFAULT NULL COMMENT '班级学号',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_year_user_grade_class`(`user_id`, `grade_id`, `class_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` bigint(20) NOT NULL COMMENT '课程ID',
  `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程code',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程名称',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_code`(`code`) USING BTREE COMMENT '课程CODE唯一索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1198846847247392769, 'Maths', '数学');
INSERT INTO `course` VALUES (1198852055046234113, 'Chinese', '语文');
INSERT INTO `course` VALUES (1201166334801879041, 'English', '英语');
INSERT INTO `course` VALUES (1201166371330072577, 'Politics', '政治');
INSERT INTO `course` VALUES (1201166424459321346, 'History', '历史');
INSERT INTO `course` VALUES (1201166444558426114, 'Geography', '地理');
INSERT INTO `course` VALUES (1201166494114127874, 'Biology', '生物');
INSERT INTO `course` VALUES (1201166515987423234, 'Physics', '物理');
INSERT INTO `course` VALUES (1201166534492692481, 'Chemistry', '化学');

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam`  (
  `id` bigint(20) NOT NULL COMMENT '测试ID',
  `grade_id` bigint(20) NOT NULL COMMENT '所在年级',
  `grade_name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '年级',
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '测试名称',
  `date` date NOT NULL COMMENT '测试时间',
  `full_marks` float NULL DEFAULT NULL COMMENT '满分',
  `class_size` int(11) NULL DEFAULT NULL COMMENT '班级人数',
  `grade_size` int(11) NULL DEFAULT NULL COMMENT '年级人数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exam
-- ----------------------------
INSERT INTO `exam` VALUES (1201178378708066306, 1201167167538995202, '2019年八年级', '2019秋八年级第一次月考', '2019-10-09', 650, 78, 630);
INSERT INTO `exam` VALUES (1201746664877658114, 1201167167538995202, '2019年八年级', '八年级上学期期中考试', '2019-10-31', 700, 78, 630);

-- ----------------------------
-- Table structure for exam_course
-- ----------------------------
DROP TABLE IF EXISTS `exam_course`;
CREATE TABLE `exam_course`  (
  `exam_id` bigint(20) NOT NULL COMMENT '测试ID',
  `course_id` bigint(20) NOT NULL COMMENT '科目ID',
  `point_scale` int(11) NOT NULL COMMENT '分制',
  `sequence_number` int(11) NULL DEFAULT NULL COMMENT '序号',
  PRIMARY KEY (`exam_id`, `course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exam_course
-- ----------------------------
INSERT INTO `exam_course` VALUES (1201178378708066306, 1198846847247392769, 120, 1);
INSERT INTO `exam_course` VALUES (1201178378708066306, 1198852055046234113, 120, 2);
INSERT INTO `exam_course` VALUES (1201178378708066306, 1201166334801879041, 120, 3);
INSERT INTO `exam_course` VALUES (1201178378708066306, 1201166371330072577, 70, 4);
INSERT INTO `exam_course` VALUES (1201178378708066306, 1201166424459321346, 50, 5);
INSERT INTO `exam_course` VALUES (1201178378708066306, 1201166444558426114, 50, 6);
INSERT INTO `exam_course` VALUES (1201178378708066306, 1201166494114127874, 50, 7);
INSERT INTO `exam_course` VALUES (1201178378708066306, 1201166515987423234, 70, 8);
INSERT INTO `exam_course` VALUES (1201746664877658114, 1198846847247392769, 120, 1);
INSERT INTO `exam_course` VALUES (1201746664877658114, 1198852055046234113, 120, 2);
INSERT INTO `exam_course` VALUES (1201746664877658114, 1201166334801879041, 120, 3);
INSERT INTO `exam_course` VALUES (1201746664877658114, 1201166371330072577, 70, 4);
INSERT INTO `exam_course` VALUES (1201746664877658114, 1201166424459321346, 50, 5);
INSERT INTO `exam_course` VALUES (1201746664877658114, 1201166444558426114, 50, 6);
INSERT INTO `exam_course` VALUES (1201746664877658114, 1201166494114127874, 100, 7);
INSERT INTO `exam_course` VALUES (1201746664877658114, 1201166515987423234, 70, 8);

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade`  (
  `id` bigint(20) NOT NULL COMMENT '年级ID',
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '年级CODE',
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '年级',
  `year` year NOT NULL COMMENT '学年',
  `start_date` date NOT NULL COMMENT '开始时间',
  `end_date` date NOT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grade
-- ----------------------------
INSERT INTO `grade` VALUES (1201167167538995202, 'EIGHTH_GRADE', '2019年八年级', 2019, '2019-09-01', '2020-07-01');

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score`  (
  `id` bigint(20) NOT NULL COMMENT '成绩ID',
  `exam_id` bigint(20) NOT NULL COMMENT '考试ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `total_score` int(11) NULL DEFAULT NULL COMMENT '用户总成绩',
  `class_ranking` int(11) NULL DEFAULT NULL COMMENT '班级排名',
  `grade_ranking` int(11) NULL DEFAULT NULL COMMENT '年级排名',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_exam_user`(`exam_id`, `user_id`) USING BTREE COMMENT '用户考试成绩唯一索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for score_detail
-- ----------------------------
DROP TABLE IF EXISTS `score_detail`;
CREATE TABLE `score_detail`  (
  `score_id` bigint(20) NOT NULL COMMENT '成绩ID',
  `course_id` bigint(20) NOT NULL COMMENT '课程ID',
  `score` float(8, 0) NULL DEFAULT NULL COMMENT '分数'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL COMMENT '人员ID',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `sex` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓别',
  `id_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1201168088784314370, '李雷', NULL, NULL, NULL);
INSERT INTO `user` VALUES (1202131281925349378, '韩梅梅', NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;

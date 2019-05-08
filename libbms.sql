/*
Navicat MySQL Data Transfer

Source Server         : bc
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : libbms

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2019-01-24 15:44:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for checkimage
-- ----------------------------
DROP TABLE IF EXISTS `checkimage`;
CREATE TABLE `checkimage` (
  `id` int(11) NOT NULL,
  `sendsampleid` int(11) DEFAULT NULL,
  `imageurl` varchar(255) DEFAULT NULL,
  `imagename` varchar(255) DEFAULT NULL,
  `sampleno` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of checkimage
-- ----------------------------

-- ----------------------------
-- Table structure for checkitem
-- ----------------------------
DROP TABLE IF EXISTS `checkitem`;
CREATE TABLE `checkitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `itemname` varchar(100) DEFAULT NULL COMMENT '检验项目名称',
  `instrumentname` varchar(100) DEFAULT NULL COMMENT '仪器名称',
  `createuser` int(11) DEFAULT NULL COMMENT '录入人',
  `createdate` datetime DEFAULT NULL COMMENT '录入时间',
  `validflag` int(1) DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of checkitem
-- ----------------------------

-- ----------------------------
-- Table structure for checkreport
-- ----------------------------
DROP TABLE IF EXISTS `checkreport`;
CREATE TABLE `checkreport` (
  `id` int(11) NOT NULL DEFAULT '0' COMMENT '主键',
  `sampleno` varchar(50) NOT NULL DEFAULT '' COMMENT '样品编号',
  `samplename` varchar(500) NOT NULL DEFAULT '' COMMENT '样品名称',
  `entrustcompany` varchar(200) DEFAULT NULL COMMENT '委托单位',
  `productionunit` varchar(200) DEFAULT NULL COMMENT '生产单位',
  `trademark` varchar(50) DEFAULT NULL COMMENT '商标',
  `receivedate` date DEFAULT NULL COMMENT '接收日期',
  `finishdate` date DEFAULT NULL COMMENT '完成日期',
  `samplemodel` varchar(50) DEFAULT NULL COMMENT '样品规格型号',
  `samplecount` varchar(100) DEFAULT NULL COMMENT '样品数量',
  `samplestatus` varchar(50) DEFAULT NULL COMMENT '样品状态',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  `createuser` int(11) DEFAULT NULL COMMENT '录入人',
  `createdate` datetime DEFAULT NULL COMMENT '录入时间',
  `resultremark` varchar(1000) DEFAULT NULL,
  `analyzeremark` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of checkreport
-- ----------------------------

-- ----------------------------
-- Table structure for checkstandard
-- ----------------------------
DROP TABLE IF EXISTS `checkstandard`;
CREATE TABLE `checkstandard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `standardcode` varchar(255) DEFAULT NULL,
  `standardcontent` varchar(255) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `validflag` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of checkstandard
-- ----------------------------
INSERT INTO `checkstandard` VALUES ('37', '', 'biaozhunneirong', '2019-01-16 16:09:03', '1');

-- ----------------------------
-- Table structure for entrustcompany
-- ----------------------------
DROP TABLE IF EXISTS `entrustcompany`;
CREATE TABLE `entrustcompany` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `entrustcompany` varchar(200) DEFAULT NULL COMMENT '委托单位',
  `username` varchar(50) DEFAULT NULL,
  `prepaymoney` float(15,2) DEFAULT '0.00',
  `createuser` int(11) DEFAULT NULL COMMENT '录入人',
  `createdate` datetime DEFAULT NULL COMMENT '录入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of entrustcompany
-- ----------------------------

-- ----------------------------
-- Table structure for groups
-- ----------------------------
DROP TABLE IF EXISTS `groups`;
CREATE TABLE `groups` (
  `id` int(11) NOT NULL,
  `groupname` varchar(100) NOT NULL,
  `privillage` varchar(200) DEFAULT NULL,
  `createuser` int(11) NOT NULL,
  `createdate` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of groups
-- ----------------------------
INSERT INTO `groups` VALUES ('1', '超级用户组', '|0|A|B|C|D-0|D-1|D-2|D-3|E-0|E-1|E-2|E-3|E-4|E-5|F-0|F-1|F-2|F-3|', '2', '2015-03-02');
INSERT INTO `groups` VALUES ('2', '室主任', '|0|A|B|C|D-0|D-1|D-2|D-3|F-0|F-3|', '2', '2015-03-03');
INSERT INTO `groups` VALUES ('3', '接样员组', '|0|A|B|C|F-0|F-1|F-2|F-3|', '2', '2015-03-03');
INSERT INTO `groups` VALUES ('4', '检验员组', '|0|B|', '2', '2015-03-03');
INSERT INTO `groups` VALUES ('5', '部门领导', '|0|A|C|D-0|D-1|D-2|E-0|E-1|E-2|E-3|', '2', '2015-03-03');
INSERT INTO `groups` VALUES ('6', '接样员兼职检验员', '|', '2', '2015-03-30');

-- ----------------------------
-- Table structure for groupuser
-- ----------------------------
DROP TABLE IF EXISTS `groupuser`;
CREATE TABLE `groupuser` (
  `id` int(11) NOT NULL,
  `groupid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of groupuser
-- ----------------------------
INSERT INTO `groupuser` VALUES ('1', '1', '2');
INSERT INTO `groupuser` VALUES ('2', '1', '40');
INSERT INTO `groupuser` VALUES ('3', '1', '41');
INSERT INTO `groupuser` VALUES ('4', '1', '42');
INSERT INTO `groupuser` VALUES ('8', '2', '10');
INSERT INTO `groupuser` VALUES ('9', '2', '43');
INSERT INTO `groupuser` VALUES ('10', '2', '44');
INSERT INTO `groupuser` VALUES ('11', '3', '19');
INSERT INTO `groupuser` VALUES ('12', '3', '20');
INSERT INTO `groupuser` VALUES ('13', '3', '45');
INSERT INTO `groupuser` VALUES ('14', '4', '49');
INSERT INTO `groupuser` VALUES ('15', '4', '3');
INSERT INTO `groupuser` VALUES ('16', '4', '4');
INSERT INTO `groupuser` VALUES ('17', '4', '5');
INSERT INTO `groupuser` VALUES ('18', '4', '6');
INSERT INTO `groupuser` VALUES ('19', '4', '7');
INSERT INTO `groupuser` VALUES ('20', '4', '8');
INSERT INTO `groupuser` VALUES ('21', '4', '9');
INSERT INTO `groupuser` VALUES ('22', '4', '11');
INSERT INTO `groupuser` VALUES ('23', '4', '12');
INSERT INTO `groupuser` VALUES ('24', '4', '13');
INSERT INTO `groupuser` VALUES ('25', '4', '14');
INSERT INTO `groupuser` VALUES ('26', '4', '15');
INSERT INTO `groupuser` VALUES ('27', '4', '16');
INSERT INTO `groupuser` VALUES ('28', '4', '17');
INSERT INTO `groupuser` VALUES ('29', '4', '18');
INSERT INTO `groupuser` VALUES ('30', '4', '46');
INSERT INTO `groupuser` VALUES ('31', '4', '47');
INSERT INTO `groupuser` VALUES ('32', '4', '48');

-- ----------------------------
-- Table structure for income
-- ----------------------------
DROP TABLE IF EXISTS `income`;
CREATE TABLE `income` (
  `id` int(11) NOT NULL DEFAULT '0',
  `entrustcompanyid` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `paymoney` float(15,2) NOT NULL DEFAULT '0.00',
  `paytype` char(1) DEFAULT NULL COMMENT '1 预付款 2 系统自动 3 按添加的登记单',
  `paydate` date NOT NULL,
  `sampleregisteids` varchar(1000) DEFAULT NULL,
  `receiveuser` varchar(50) NOT NULL,
  `status` char(1) DEFAULT '0',
  `createuser` int(11) NOT NULL,
  `createdate` date NOT NULL,
  `userid` int(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `taxtype` varchar(255) DEFAULT NULL,
  `billischeck` varchar(255) DEFAULT NULL,
  `caltype` varchar(255) DEFAULT NULL,
  `precal` varchar(255) DEFAULT NULL,
  `billno` varchar(255) DEFAULT NULL,
  `totalcheckmoney` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of income
-- ----------------------------

-- ----------------------------
-- Table structure for instrument
-- ----------------------------
DROP TABLE IF EXISTS `instrument`;
CREATE TABLE `instrument` (
  `id` int(11) NOT NULL COMMENT '主键',
  `codename` varchar(200) NOT NULL COMMENT '编码和名称',
  `inscode` varchar(50) DEFAULT NULL,
  `insname` varchar(100) DEFAULT NULL,
  `standard` varchar(100) DEFAULT NULL COMMENT '规格型号',
  `measue_range` varchar(100) DEFAULT NULL COMMENT '测量范围',
  `grade` varchar(100) DEFAULT NULL COMMENT '准确度等级',
  `product` varchar(300) DEFAULT NULL COMMENT '生产单位',
  `correct_company` varchar(300) DEFAULT NULL COMMENT '校准单位',
  `correct_cycle` varchar(50) DEFAULT NULL COMMENT '校准周期',
  `recent_correct_date` date DEFAULT NULL COMMENT '最近校准日期',
  `next_correct_date` date DEFAULT NULL COMMENT '下次校准日期',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `validflag` int(1) DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of instrument
-- ----------------------------
INSERT INTO `instrument` VALUES ('1', 'SB-002元素分析仪', 'SB-002', '元素分析仪', '', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('2', 'SB-081（离子色谱仪）', 'SB-081', '离子色谱仪', 'ED50', '', '', '美国戴安公司', '', '', null, null, '已坏', '1');
INSERT INTO `instrument` VALUES ('3', 'SB-192（电感耦合等离子体发射光谱仪）', 'SB-192', '电感耦合等离子体发射光谱仪', 'Icap6300', '', '不确定度U=2%(K=2)', 'thermo', '北京市计量检测科学研究院', '检定2年', '2007-11-09', '2009-11-08', '', '1');
INSERT INTO `instrument` VALUES ('4', 'SB-214（傅里叶变换红外光谱仪）', 'SB-214', '傅里叶变换红外光谱仪', 'Nicolet6700', '', '', '美国THERMO', '', '', null, null, '已被武彦文博士搬到西三环', '1');
INSERT INTO `instrument` VALUES ('5', 'SB-216（纯水超纯水一体化体统）', 'SB-216', '纯水超纯水一体化体统', 'Millipore Gradient', '', '', '美国密理博', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('6', 'SB-217（薄层扫描仪）', 'SB-217', '薄层扫描仪', 'CS-9301CS', '', '0.3nm', '日本岛津公司', '中国计量科学研究院', '两年', '2009-02-18', '2010-02-17', '', '1');
INSERT INTO `instrument` VALUES ('7', 'SB-218(PCR扩增仪)', 'SB-218', 'PCR扩增仪', 'Bio-Rad PTC 220', '', '', '美国BIO公司', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('8', 'SB-219（酶标仪、洗板机）', 'SB-219', '酶标仪、洗板机', 'Bio-Rad', '', '', '美国BIO公司', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('9', 'SB-220（电热恒温干燥箱）', 'SB-220', '电热恒温干燥箱', 'DHG-9071A', '', '', '上海精宏', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('10', 'SB-221（生化培养箱）', 'SB-221', '生化培养箱', 'SHP-150', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('11', 'SB-222（生化培养箱）', 'SB-222', '生化培养箱', 'GNP-9080', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('12', 'SB-223（高压灭菌锅）', 'SB-223', '高压灭菌锅', 'MLS-3750', '', '', '日本三洋', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('13', 'SB-224（分析天平）', 'SB-224', '分析天平', 'CP225D', '十万分之一', 'E2等级', 'sartorius', '北京市海淀区计量检测所', '一年', '2009-03-20', '2010-03-19', '', '1');
INSERT INTO `instrument` VALUES ('14', 'SB-225(紫外可见分光光度计）', 'SB-225', '紫外可见分光光度计', 'UV-2550', '', '透射比：U=0.2%T(k=2) 波长U=0.2nm（k=2）杂散光U=0.2nm（k=2）', '日本岛津公司', '北京市海淀区计量检测所', '二年', '2009-02-12', '2011-02-11', '', '1');
INSERT INTO `instrument` VALUES ('15', 'SB-226（气相色谱仪）', 'SB-226', '气相色谱仪', 'CN10801021', '温度;(0-400℃) 标物：       TCD:(0.1-500)mg/mL       FID:0.1-104)ng/μL         FPD:(0.1-103)ng/μL       EC', '温度：0.03% 标物：3%  k=2 注射器：1% 载气流速：（流量计）：1%', '美国安捷伦公司', '中国计量科学研究院', '两年', '2009-02-18', '2011-02-17', '', '1');
INSERT INTO `instrument` VALUES ('16', 'SB-227(液相色谱仪)', 'SB-227', '液相色谱仪', 'waters 2424', '1.0×10-4g/mL     标准物质浓  度:(1.0×10-4g/mL-1.0×10-9g/mL)', '标准物质:(3-4)% k=2', '美国waters公司', '中国计量科学研究院', '两年\r\n', '2009-02-18', '2011-02-17', '', '1');
INSERT INTO `instrument` VALUES ('17', 'SB-228(液相色谱仪)', 'SB-228', '液相色谱仪', 'Acquity', '标准物质浓  度:(1.0×10-4g/mL-1.0×10-9g/mL)', '标准物质:(3-4)% k=2', '美国waters公司', '中国计量科学研究院', '两年\r\n', '2009-02-18', '2011-02-17', '', '1');
INSERT INTO `instrument` VALUES ('18', 'SB-229（高效液相色谱仪）', 'SB-229', '高效液相色谱仪', '', '', '', '日本日立', '', '', null, null, '长期不开', '1');
INSERT INTO `instrument` VALUES ('19', 'SB-230（气相色谱-质谱联用仪）', 'SB-230', '气相色谱-质谱联用仪', 'GCMS-QP2010 PLUS', '八氟萘-异辛烷 100pg/μl  六氯苯-异辛烷 10.0ng/μl 二苯甲酮-异辛烷 10.0ng/μl      硬脂酸甲酯-异辛烷 10.0ng/μl', '3%（k=2）', '日本岛津公司', '中国计量科学研究院', '两年', '2009-02-18', '2011-02-17', '', '1');
INSERT INTO `instrument` VALUES ('20', 'SB-231（离心机）', 'SB-231', '离心机', 'Allegra X-15R', '', '', '美国BECKMAN', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('21', 'SB-232（离心机）', 'SB-232', '离心机', 'CF 16RX Ⅱ', '', '', '日立公司', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('22', 'SB-233（分析天平）', 'SB-233', '分析天平', 'BS224S', '万分之一', 'E2等级', '赛多利斯科学仪器（北京）有限公司', '北京市海淀区计量检测所', '一年', '2009-02-09', '2010-02-08', '', '1');
INSERT INTO `instrument` VALUES ('23', 'SB-234（紫外可见分光光度计）', 'SB-234', '紫外可见分光光度计', 'UV-1800', '', '', '日本岛津', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('24', 'SB-235（实时荧光PCR仪）', 'SB-235', '实时荧光PCR仪', '7500fast', '', '', 'ABI/美国', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('25', 'SB-236(全自动基因分析仪)', 'SB-236', '全自动基因分析仪', '3130XL', '', '', 'ABI/美国', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('26', 'SB-237（数码显微镜）', 'SB-237', '数码显微镜', 'VHX-600E', '', '', 'KEYENCE/日本', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('27', 'SB-238(冷冻干燥机)', 'SB-238', '冷冻干燥机', 'ALPHA1-2LD PLUS', '', '', 'CHRIST/德国', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('28', 'SB-239(索氏提取仪)', 'SB-239', '索氏提取仪', '', '', '', 'FOSS/丹麦', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('29', 'SB-240（真空干燥箱）', 'SB-240', '真空干燥箱', 'DZF-6050', '', '', '上海', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('30', 'SB-241（旋转蒸发仪）', 'SB-241', '旋转蒸发仪', 'BUCHI', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('31', 'SB-242（分析天平）', 'SB-242', '分析天平', 'CP224S', '万分之一', 'E2等级', 'sartorius', '北京市海淀区计量检测所', '一年', '2009-02-09', '2010-02-08', '', '0');
INSERT INTO `instrument` VALUES ('32', 'SB-243（凝胶净化色谱仪）', 'SB-243', '凝胶净化色谱仪', 'ULTRA', '', '', 'LCTECH德国', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('33', 'SB-244（毛细管电泳仪）', 'SB-244', '毛细管电泳仪', 'PACE/MDQ', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('34', 'SB-245（pH计）', 'SB-245', 'pH计', 'PDS-307W', '', '', '上海理达公司', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('35', 'SB-246 凝胶渗透色谱仪（RI、LS）', 'SB-246', '凝胶渗透色谱仪（RI、LS）', '', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('36', 'SB-246（凝胶渗透色谱仪）', 'SB-246', '凝胶渗透色谱仪', 'DAWN HELLO', '(2000～2000000) mol/g', '二级标准物：U=8.6% (k=2)', 'Wyatt', '中国计量科学研究院', '检定2年', '2009-04-01', '2011-04-01', '', '1');
INSERT INTO `instrument` VALUES ('37', 'SB-246（凝胶渗透色谱仪）', 'SB-246', '凝胶渗透色谱仪', 'DAWN HELLO', '(2000～2000000) mol/g', '二级标准物：U=8.6% (k=2)', 'Wyatt', '国家计量院', '检定2年', '2009-01-09', '2011-01-08', '', '1');
INSERT INTO `instrument` VALUES ('38', 'SB-247（荧光显微镜）', 'SB-247', '荧光显微镜', 'Zeiss', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('39', 'SB-248（分析天平）', 'SB-248', '分析天平', 'TE 3102S', '', '', '赛多利斯', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('40', 'SB-249（分析天平）', 'SB-249', '分析天平', 'UW-620H', '', '', '日本岛津', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('41', 'SB-250（分析天平）', 'SB-250', '分析天平', 'UW-4200H', '', '', '日本岛津', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('42', 'SB-251（磁力搅拌器）', 'SB-251', '磁力搅拌器', 'EMS-3A', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('43', 'SB-252（水浴锅）', 'SB-252', '水浴锅', 'DK-420', '', '', '上海精宏', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('44', 'SB-253（摇床)', 'SB-253', '摇床', 'THZ-C', '', '', '江苏太仓', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('45', 'SB-254(培养箱)', 'SB-254', '培养箱', 'GNP-9080', '', '', '上海精宏', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('46', 'SB-255(pH计)', 'SB-255', 'pH计', '', '', '', '赛多利斯', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('47', 'SB-256(冰箱)', 'SB-256', '冰箱', 'BCD-254', '', '', '西门子', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('48', 'SB-257(超净台)', 'SB-257', '超净台', 'BCM-1300A', '', '', '安泰', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('49', 'SB-258(超净台)', 'SB-258', '超净台', 'BCN-1360', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('50', 'SB-259(生物安全柜)', 'SB-259', '生物安全柜', 'BHC-1300ⅡA2', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('51', 'SB-260(干燥箱)', 'SB-260', '干燥箱', 'DHG-9070', '', '', '上海精宏', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('52', 'SB-261(球磨仪)', 'SB-261', '球磨仪', 'MM400', '', '', '德国Retsch', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('53', 'SB-262(超低温冰箱)', 'SB-262', '超低温冰箱', 'VXE-490', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('54', 'SB-263(电泳仪)', 'SB-263', '电泳仪', 'DYY-6C', '', '', '北京六一', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('55', 'SB-264(制冰机)', 'SB-264', '制冰机', 'SIM-F140', '', '', '日本三洋', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('56', 'SB-265(电转仪)', 'SB-265', '电转仪', '', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('57', 'SB-266(恒温金属浴)', 'SB-266', '恒温金属浴', '', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('58', 'SB-267(涡旋振荡器)', 'SB-267', '涡旋振荡器', '', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('59', 'SB-268(低速离心机)', 'SB-268', '低速离心机', 'TDL-60C', '', '', '上海', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('60', 'SB-269(旋转蒸发仪)', 'SB-269', '旋转蒸发仪', 'EYELA', '', '', '爱郎', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('61', 'SB-270(涡旋器)', 'SB-270', '涡旋器', 'WH-3', '', '', '上海沪西', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('62', 'SB-271(水式多用真空泵)', 'SB-271', '水式多用真空泵', 'SHB', '', '', '郑州长城工贸公司', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('63', 'SB-272(摇床)', 'SB-272', '摇床', 'HY-4A', '', '', '江苏荣华', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('64', 'SB-273(混匀器)', 'SB-273', '混匀器', 'STR4', '', '', '美国stuart', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('65', 'SB-274(氮吹仪)', 'SB-274', '氮吹仪', '12位水浴N-EAVPTM111', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('66', 'SB-275(氮吹仪)', 'SB-275', '氮吹仪', '24位水浴', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('67', 'SB-276(电热板)', 'SB-276', '电热板', 'EH20A', '', '', '美国labtech', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('68', 'SB-277(程控箱式电炉)', 'SB-277', '程控箱式电炉', 'SXL-1208', '(300～1300)℃', '一等  550℃', '上海精密实验设备有限公司', '北京市海淀区计量检测所', '一年', '2009-02-12', '2010-02-11', '', '1');
INSERT INTO `instrument` VALUES ('69', 'SB-278(离心机)', 'SB-278', '离心机', '5804R', '', '', '德国艾本德', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('70', 'SB-279(自动部分收集器)', 'SB-279', '自动部分收集器', '', '', '', 'BSZ琪特', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('71', 'SB-280(液氮容器)', 'SB-280', '液氮容器', '', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('72', 'SB-281(均质器)', 'SB-281', '均质器', '', '', '', 'Easy mix', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('73', 'SB-282(移液器)', 'SB-282', '移液器', '', '', '', 'eppendorf德国', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('74', 'SB-283(真空离心浓缩仪)', 'SB-283', '真空离心浓缩仪', '', '', '', 'eppendorf德国', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('75', 'SB-284(层析冷柜)', 'SB-284', '层析冷柜', 'YC-1', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('76', 'SB-285(高速冷冻离心机)', 'SB-285', '高速冷冻离心机', 'CR22GⅡ', '', '', '日立日本', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('77', 'SB-286(石墨高温电热消解仪)', 'SB-286', '石墨高温电热消解仪', 'EHD36', '', '', '美国labtech', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('78', 'SB-287(全自动索氏提取仪)', 'SB-287', '全自动索氏提取仪', '', '', '', 'FOSS/丹麦', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('79', 'SB-288(凝胶成像系统)', 'SB-288', '凝胶成像系统', 'Bio-Rad', '', '', 'Bio-Rad公司美国', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('80', 'SB-289(全自动基因分析仪)', 'SB-289', '全自动基因分析仪', '3730XL', '', '', 'Bio-Rad公司美国', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('81', 'SB-290(纯水-超纯水一体机)', 'SB-290', '纯水-超纯水一体机', 'Gradient', '', '', '美国密理博公司', '', '', null, null, '生物', '1');
INSERT INTO `instrument` VALUES ('82', 'SB-291(纯水-超纯水一体机)', 'SB-291', '纯水-超纯水一体机', 'Gradient', '', '', '美国密理博公司', '', '', null, null, '材料', '1');
INSERT INTO `instrument` VALUES ('83', 'SB-292(干燥箱)', 'SB-292', '干燥箱', 'DHG-9140A', '', '', '上海精宏', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('84', 'SB-293(冷却水循环机)', 'SB-293', '冷却水循环机', '', '', '', '美国labtech', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('85', 'SB-294（原子荧光分光光度计/形态分析仪）', 'SB-294', '原子荧光分光光度计/形态分析仪', 'AFS9130', '', 'U=0.04ng(k=2)', '北京吉天仪器公司', '北京市计量检测科学研究院', '检定2年', '2009-03-16', '2009-03-15', '', '1');
INSERT INTO `instrument` VALUES ('86', 'SB-295(制冰机)', 'SB-295', '制冰机', '', '', '', '北京德天佑', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('87', 'SB-296（分析天平）', 'SB-296', '分析天平', 'UW620H', '千分之一', 'E2等级', '日本岛津公司', '北京市海淀区计量检测所', '一年', '2009-02-09', '2010-02-08', '', '1');
INSERT INTO `instrument` VALUES ('88', 'SB-297(差示扫描量热仪)', 'SB-297', '差示扫描量热仪', 'DSC2000', '', '', 'TA公司美国', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('89', 'SB-299(红外光谱仪)', 'SB-299', '红外光谱仪', 'Spectrum400200', '', '', 'PE公司', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('90', 'SB-300(离心机)', 'SB-300', '离心机', '', '', '', 'BACKMAN美国', '', '', null, null, '第2台', '1');
INSERT INTO `instrument` VALUES ('91', 'SB-301(三用紫外灯)', 'SB-301', '三用紫外灯', 'WFH-203', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('92', 'SB-350(PCR扩增仪)', ' SB-350', 'PCR扩增仪', 'ABI9700', '', '', '美国ABI公司', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('93', 'SB-351(浊度计)', 'SB-351', '浊度计', 'WGZ-1', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('94', 'SB-352(干燥箱)', 'SB-352', '干燥箱', '', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('95', 'SB-353(水质分析仪)', 'SB-353', '水质分析仪', 'DR2800', '', '', '哈西公司美国', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('96', 'SB-354(防爆冰箱)', 'SB-354', '防爆冰箱', 'EX ', '', '', '上海钧华公司', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('97', 'SB-355(生化培养箱)', 'SB-355', '生化培养箱', 'SPX-150B-Z', '', '', '上海博讯公司', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('98', 'SB-356(定氮仪)', 'SB-356', '定氮仪', 'DDY-2A', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('99', 'SB-357(BOD测试仪)', 'SB-357', 'BOD测试仪', '', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('100', 'SB-358（分析天平）', 'SB-358', '分析天平', 'XS205', '十万分之一', 'E2等级', 'METTLER TOLEDO', '北京市海淀区计量检测所', '一年', '2009-03-20', '2010-03-19', '', '1');
INSERT INTO `instrument` VALUES ('101', 'SB-359(旋光仪)', 'SB-359', '旋光仪', 'WXG-4', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('102', 'SB-360(分析天平)', 'SB-360', '分析天平', 'PL402-L', '', '', '梅特勒公司', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('103', 'SB-361(折射仪)', 'SB-361', '折射仪', 'WAY-2W', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('104', 'SB-362(灭菌锅)', 'SB-362', '灭菌锅', 'HG-80', '', '', 'HIRAYAMA ', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('105', 'SB-363(灭菌锅)', 'SB-363', '灭菌锅', 'MLS-3750', '', '', 'SANYO ', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('106', 'SB-364(干燥箱)', 'SB-364', '干燥箱', 'ZRD-5030', '', '', '上海智城', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('107', 'SB-365(生化培养箱)', 'SB-365', '生化培养箱', 'ZSD-A1090', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('108', 'SB-366(气相色谱仪)', 'SB-366', '气相色谱仪', '7890A', '', '', 'Agilent美国', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('109', 'SB-367(原子吸收光谱仪)', 'SB-367', '原子吸收光谱仪', 'AA7003', '', '', '北京东西电子公司', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('110', 'SB-368(分光光度计)', 'SB-368', '分光光度计', '', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('111', 'SB-369(致病菌免疫磁分离系统)', 'SB-369', '致病菌免疫磁分离系统', '', '', '', 'Matrix', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('112', 'SB-370(霉菌培养箱)', 'SB-370', '霉菌培养箱', 'ZJD-1150B', '', '', '上海智城', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('113', 'SB-371(农药残留快速检测仪)', 'SB-371', '农药残留快速检测仪', '', '', '', '吉大小天鹅', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('114', 'SB-372(食品五合一快速检测仪)', 'SB-372', '食品五合一快速检测仪', '', '', '', '吉大小天鹅', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('115', 'SB-373(多功能甲醛快速检测仪)', 'SB-373', '多功能甲醛快速检测仪', '', '', '', '吉大小天鹅', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('116', 'SB-374(冰箱)', 'SB-374', '冰箱', 'MPR-414F', '', '', 'SANYO 美国', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('117', 'SB-375(全自动新型生化培养箱)', 'SB-375', '全自动新型生化培养箱', 'ZSD-1270', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('118', 'SB-376(离心机)', 'SB-376', '离心机', 'Eppendorf 5424', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('119', 'SB-377(PCR仪)', 'SB-377', 'PCR仪', 'S100', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('120', 'SB-378(时段编程鼓风干燥箱)', 'SB-378', '时段编程鼓风干燥箱', 'ZRD 5030', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('121', 'SB-379(酸度计)', 'SB-379', '酸度计', '', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('122', 'SB-380(电导率仪)', 'SB-380', '电导率仪', '', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('123', 'SB-381(分析天平)', 'SB-381', '分析天平', 'PL402-L', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('124', 'SB-382(分析天平)', 'SB-382', '分析天平', 'Shimazd vw620H', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('125', 'SB-383(冰箱)', 'SB-383', '冰箱', '', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('126', 'SB-384(冰箱)', 'SB-384', '冰箱', '', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('127', 'SB-385(冰箱)', 'SB-385', '冰箱', '', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('128', 'SB-386(冰箱)', 'SB-386', '冰箱', '', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('129', 'SB-387(冰箱)', 'SB-387', '冰箱', '', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('130', 'SB-388(冰箱)', 'SB-388', '冰箱', '', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('131', 'SB-389(冰箱)', 'SB-389', '冰箱', '', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('132', 'SB-390(冰箱)', 'SB-390', '冰箱', '', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('133', 'SB-391(冰箱)', 'SB-391', '冰箱', '', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('134', 'SB-392(冰箱)', 'SB-392', '冰箱', '', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('135', 'SB-393(稳定性检测仪)', 'SB-393', '稳定性检测仪', 'WD-A', '', '', '天津药典', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('136', 'SB-394(全自动卡式水分测定仪)', 'SB-394', '全自动卡式水分测定仪', 'ZDJ-2S', '', '', '北京先驱', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('137', 'SB-395(全自动电位滴定仪)', 'SB-395', '全自动电位滴定仪', 'ZDJ-2D', '', '', '北京先驱', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('138', 'SB-396(智能溶出试验仪)', 'SB-396', '智能溶出试验仪', 'ZRS-8G', '', '', '天大天发', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('139', 'SB-397(澄明度检测仪)', 'SB-397', '澄明度检测仪', 'YB-2', '', '', '天大天发', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('140', 'SB-398(智能崩解仪)', 'SB-398', '智能崩解仪', 'ZB-1E', '', '', '天大天发', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('141', 'SB-399(旋转式粘度计)', 'SB-399', '旋转式粘度计', 'NDJ-5S', '', '', '上海越平', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('142', 'SB-400(紫外光耐气候试验箱)', 'SB-400', '紫外光耐气候试验箱', 'ZN-P', '', '', '常州市国立试验设备研究所', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('143', 'SB-401(乌式粘度仪)', 'SB-401', '乌式粘度仪', 'SYP-D', '', '', '上海良晶', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('144', 'SB-402(PCR核酸扩增仪)', 'SB-402', 'PCR核酸扩增仪', 'ABI-9700', '', '', '美国BIO公司', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('145', 'SB-403(微波消解萃取仪)', 'SB-403', '微波消解萃取仪', 'ETHOS1', '', '', 'Milestone', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('146', 'SB-404(蛋白质纯化仪)', 'SB-404', '蛋白质纯化仪', 'AKTAPurifier100', '', '', 'GE Healthcare', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('147', 'SB-405(二氧化碳培养箱)', 'SB-405', '二氧化碳培养箱', '371', '', '', 'Thermo Fisher', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('148', 'SB-406(二氧化碳罐)', 'SB-406', '二氧化碳罐', '40L', '', '', '北京圣达', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('149', 'SB-407(高压细胞破碎仪)', 'SB-407', '高压细胞破碎仪', '5020PB', '', '', '西盟国际', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('150', 'SB-408(生化培养箱)', 'SB-408', '生化培养箱', 'ZSD-A1270', '', '', '北京东南', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('151', 'SB-409(酸度计)', 'SB-409', '酸度计', 'S20K SebenEas Ytm pH', '', '', '梅特勒', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('152', 'SB-410(恒温鼓风干燥箱)', 'SB-410', '恒温鼓风干燥箱', 'ZRD-5110', '', '', '上海智城', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('153', 'SB-411(酸缸)', 'SB-411', '酸缸', '550*350*350', '', '', '北京科有', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('154', 'SB-412(真空吸引器)', 'SB-412', '真空吸引器', 'BioVac 225', '', '', 'Wiggens', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('155', 'SB-413(干式恒温器)', 'SB-413', '干式恒温器', 'MK-10-C', '', '', '杭州奥盛', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('156', 'SB-414(涡旋振荡器)', 'SB-414', '涡旋振荡器', 'Vortex-Genie-2', '', '', '奥然科技', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('157', 'SB-415(涡旋振荡器)', 'SB-415', '涡旋振荡器', 'Vortex-Genie-2', '', '', '奥然科技', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('158', 'SB-416(涡旋振荡器)', 'SB-416', '涡旋振荡器', 'Vortex-Genie-2', '', '', '奥然科技', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('159', 'SB-417(涡旋振荡器)', 'SB-417', '涡旋振荡器', 'Vortex-Genie-2', '', '', '奥然科技', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('160', 'SB-418(氙灯老化试验箱)', 'SB-418', '氙灯老化试验箱', 'ZN-66F', '', '', '北京鸿达', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('161', 'SB-419(超速冷冻离心机)', 'SB-419', '超速冷冻离心机', 'Optima L-90K', '', '', '美国BECKMAN', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('162', 'SB-420(海尔立式透明门冷藏柜)', 'SB-420', '海尔立式透明门冷藏柜', 'SC-329GA', '', '', '海尔', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('163', 'SB-421(西门子冰箱)', 'SB-421', '西门子冰箱', 'BCD-254', '', '', '西门子', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('164', 'SB-422(低温高速离心机)', 'SB-422', '低温高速离心机', 'CF16RXⅡ', '', '', '日立', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('165', 'SB-423(恒温混匀仪)', 'SB-423', '恒温混匀仪', 'THERMIXER COMPACT', '', '', 'eppendorf德国', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('166', 'SB-424(离心机)', 'SB-424', '离心机', '5424', '', '', 'eppendorf德国', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('167', 'SB-425(低温高速离心机)', 'SB-425', '低温高速离心机', '5817R', '', '', 'eppendorf德国', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('168', 'SB-426(分析天平)', 'SB-426', '分析天平', 'UW-620H', '', '', '岛津', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('169', 'SB-427(薄层色谱配套分析系统)', 'SB-427', '薄层色谱配套分析系统', 'ATS4', '', '', '瑞士卡玛', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('170', 'SB-428低温水浴GD120', 'SB-428', '低温水浴GD120', 'GD120', '', '', 'Grant', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('171', 'SB-429高压灭菌锅HG-80', 'SB-429', '高压灭菌锅HG-80', 'HG-80', '', '', 'HIRAYAMA日本', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('172', 'SB-430半制备液相', 'SB-430', '半制备液相', 'waters 1525/2998/2414/2465', '', '', '美国waters公司', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('173', 'SB-431超高效液相色谱-质谱联用仪', 'SB-431', '超高效液相色谱-质谱联用仪', 'Acquity-Xevo TQ', '', '', '美国waters公司', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('174', 'SB-432离心机', 'SB-432', '离心机', 'CR22GⅢ', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('175', 'SB-434多功能酶标仪', 'SB-434', '多功能酶标仪', 'Varioskan Flash', '', '', '美国Thermo Fisher', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('176', 'SB-435倒置显微镜', 'SB-435', '倒置显微镜', 'CKX31', '', '', '奥林巴斯', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('177', 'SB-436紫外分光光度计', 'SB-436', '紫外分光光度计', 'Biospec-nona', '', '', '岛津', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('178', 'SB-437PCR仪', 'SB-437', 'PCR仪', 'C-1000', '', '', '上海伯乐', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('179', 'SB-438 半制备液相色谱仪', 'SB-438', '半制备液相色谱仪', 'LC-6AD', '', '', '日本岛津', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('180', 'SB-439 恒温恒湿箱', 'SB-439', '恒温恒湿箱', 'BPS-100CL', '', '', '一恒仪器', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('181', 'SB-440 电感耦合等离子体质谱仪', 'SB-440', '电感耦合等离子体质谱仪', '7700X', '', '', '美国安捷伦', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('182', 'SB-448 凝胶渗透色谱（RI检测器）', 'SB-448', '凝胶渗透色谱（RI检测器）', '', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('183', 'SB-496Y全自动凯氏定氮仪', 'SB-496Y', '全自动凯氏定氮仪', '', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('184', 'SB-497Y全自动电位滴定仪', 'SB-497Y', '全自动电位滴定仪', '', '', '', '', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('185', 'SB-498Y气相色谱仪', 'SB-498Y', '气相色谱仪', '', '', '', '天美GC7980-TCD/FID', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('186', 'SB-501Y 进样瓶清洗机', 'SB-501Y', '进样瓶清洗机', 'TTL-100', '辅助设备', '辅助设备', '北京同泰联科技发展有限公司', '', '', null, null, '', '1');
INSERT INTO `instrument` VALUES ('187', '', '', '', 'waters 2695/waters 2998', '标准物质浓  度:(1.0×10-4g/mL-1.0×10-9g/mL)', '标准物质:(3-4)% k=2', '美国waters公司', '中国计量科学研究院', '两年\r\n', '2009-02-18', '2011-02-17', '', '1');

-- ----------------------------
-- Table structure for labprocess
-- ----------------------------
DROP TABLE IF EXISTS `labprocess`;
CREATE TABLE `labprocess` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `labprocesstitle` varchar(255) DEFAULT NULL COMMENT '实验标题',
  `labprocesscontent` varchar(4000) DEFAULT NULL,
  `createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `validflag` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of labprocess
-- ----------------------------

-- ----------------------------
-- Table structure for labprocesscontent
-- ----------------------------
DROP TABLE IF EXISTS `labprocesscontent`;
CREATE TABLE `labprocesscontent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `labprocesscontent` varchar(255) DEFAULT NULL COMMENT '实验过程状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of labprocesscontent
-- ----------------------------

-- ----------------------------
-- Table structure for menufunction
-- ----------------------------
DROP TABLE IF EXISTS `menufunction`;
CREATE TABLE `menufunction` (
  `id` int(11) NOT NULL,
  `code` varchar(5) NOT NULL,
  `menuname` varchar(50) NOT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单连接地址',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单的图标名称',
  `order` int(3) DEFAULT '0',
  `remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menufunction
-- ----------------------------
INSERT INTO `menufunction` VALUES ('1', 'A', '样品登记', '/business/registe/listSampleRegiste.html', '/img/Save.png', '1', null);
INSERT INTO `menufunction` VALUES ('2', 'B', '任务分配', '/business/send/listSendSample.html', '/img/Send.png', '2', null);
INSERT INTO `menufunction` VALUES ('3', 'C', '报告编制', '/business/checkreport/listCheckReport.html', '/img/Scenario.png', '3', null);
INSERT INTO `menufunction` VALUES ('4', 'D-0', '统计分析', '', '/img/Time.png', '9', null);
INSERT INTO `menufunction` VALUES ('5', 'E-0', '系统管理', '', '/img/Touch.png', '13', null);
INSERT INTO `menufunction` VALUES ('6', 'E-1', '检验项目管理', '/business/admin/checkitem/listCheckItem.html', null, '14', null);
INSERT INTO `menufunction` VALUES ('7', 'E-2', '仪器管理', '/business/admin/instrument/listInstrument.html', null, '15', null);
INSERT INTO `menufunction` VALUES ('8', 'E-3', '员工管理', '/business/admin/user/listUser.html', null, '16', null);
INSERT INTO `menufunction` VALUES ('9', 'D-1', '收入统计', '/business/statistics/forregiste/listregisteStatistics.html', null, '10', null);
INSERT INTO `menufunction` VALUES ('10', 'D-2', '委托单位统计', '/business/statistics/forcompany/listcompanyStatistics.html', null, '11', null);
INSERT INTO `menufunction` VALUES ('11', 'D-3', '员工工作量统计', '/business/statistics/foremployee/listemployeeStatistics.html', null, '12', null);
INSERT INTO `menufunction` VALUES ('12', 'F-0', '结算发报告', null, '/img/worker.png', '4', null);
INSERT INTO `menufunction` VALUES ('13', 'F-1', '预 付 款', '/business/financial/prepay/listPrepayment.html', null, '6', null);
INSERT INTO `menufunction` VALUES ('14', 'F-2', '结算发报告', '/business/financial/income/listIncome.html', null, '5', null);
INSERT INTO `menufunction` VALUES ('15', 'F-3', '综合查询', '/business/financial/query/listpayStatistics.html', null, '7', null);
INSERT INTO `menufunction` VALUES ('16', 'F-5', '综合统计', '/business/financial/query/listTj.html', null, '8', null);
INSERT INTO `menufunction` VALUES ('17', 'E-4', '检验标准管理', '/business/admin/checkstandard/listCheckStandard.html', null, '0', null);
INSERT INTO `menufunction` VALUES ('18', 'E-5', '实验过程管理', '/business/admin/labprocess/listLabProcess.html', null, '0', null);
INSERT INTO `menufunction` VALUES ('100', '0', '首页    ', '/index/showinfo.html', '/img/flag.png', '0', null);

-- ----------------------------
-- Table structure for offices
-- ----------------------------
DROP TABLE IF EXISTS `offices`;
CREATE TABLE `offices` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of offices
-- ----------------------------
INSERT INTO `offices` VALUES ('1', '部门领导');
INSERT INTO `offices` VALUES ('2', '分析室');
INSERT INTO `offices` VALUES ('3', '检测室');
INSERT INTO `offices` VALUES ('4', '天然产物室');

-- ----------------------------
-- Table structure for prepayment
-- ----------------------------
DROP TABLE IF EXISTS `prepayment`;
CREATE TABLE `prepayment` (
  `id` int(11) NOT NULL DEFAULT '0',
  `entrustcompanyid` int(11) NOT NULL COMMENT '委托单位',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `username` varchar(50) NOT NULL COMMENT '付款人',
  `tel` varchar(50) NOT NULL COMMENT '联系电话',
  `prepaymoney` float(15,2) NOT NULL DEFAULT '0.00' COMMENT '预付金额',
  `prepaydate` date NOT NULL COMMENT '付款日期',
  `receiveruser` varchar(20) NOT NULL COMMENT '接收人',
  `createuser` int(11) DEFAULT NULL,
  `createdate` date DEFAULT NULL,
  `userid` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of prepayment
-- ----------------------------

-- ----------------------------
-- Table structure for productcompany
-- ----------------------------
DROP TABLE IF EXISTS `productcompany`;
CREATE TABLE `productcompany` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productcompany` varchar(200) DEFAULT NULL COMMENT '委托单位',
  `createuser` int(11) DEFAULT NULL COMMENT '录入人',
  `createdate` datetime DEFAULT NULL COMMENT '录入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of productcompany
-- ----------------------------

-- ----------------------------
-- Table structure for qualification
-- ----------------------------
DROP TABLE IF EXISTS `qualification`;
CREATE TABLE `qualification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` int(5) NOT NULL DEFAULT '0' COMMENT '资质',
  `name` varchar(100) NOT NULL COMMENT '资质名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qualification
-- ----------------------------
INSERT INTO `qualification` VALUES ('1', '1', 'CMA');
INSERT INTO `qualification` VALUES ('2', '2', 'CNAS');
INSERT INTO `qualification` VALUES ('3', '3', 'CMAF');
INSERT INTO `qualification` VALUES ('4', '4', '其他');

-- ----------------------------
-- Table structure for registecheckitem
-- ----------------------------
DROP TABLE IF EXISTS `registecheckitem`;
CREATE TABLE `registecheckitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sampleregisteid` int(11) NOT NULL COMMENT '登记单主键',
  `checkitem` int(11) DEFAULT NULL COMMENT '检验项目',
  `examineuser` int(11) DEFAULT NULL COMMENT '检验员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of registecheckitem
-- ----------------------------

-- ----------------------------
-- Table structure for relationuser
-- ----------------------------
DROP TABLE IF EXISTS `relationuser`;
CREATE TABLE `relationuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `senduser` varchar(50) DEFAULT NULL COMMENT '送样人',
  `tel` varchar(100) DEFAULT NULL COMMENT '联系方式',
  `address` varchar(200) DEFAULT NULL COMMENT '联系地址',
  `createuser` int(11) DEFAULT NULL COMMENT '录入人',
  `createdate` datetime DEFAULT NULL COMMENT '录入时间',
  `entrustcompanyid` int(11) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `type` char(10) DEFAULT NULL,
  `balance` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of relationuser
-- ----------------------------
INSERT INTO `relationuser` VALUES ('1', 'songyangren', 'lianxi', 'dizhi', '2', '2019-01-22 13:54:53', '1', '939129504@qq.com', '1', '0.0');

-- ----------------------------
-- Table structure for samplecategory
-- ----------------------------
DROP TABLE IF EXISTS `samplecategory`;
CREATE TABLE `samplecategory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(100) NOT NULL,
  `remain` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of samplecategory
-- ----------------------------
INSERT INTO `samplecategory` VALUES ('1', '日常样品', null);
INSERT INTO `samplecategory` VALUES ('2', '科研任务', null);
INSERT INTO `samplecategory` VALUES ('3', '综合分析', null);
INSERT INTO `samplecategory` VALUES ('4', '其他', null);

-- ----------------------------
-- Table structure for sampleregiste
-- ----------------------------
DROP TABLE IF EXISTS `sampleregiste`;
CREATE TABLE `sampleregiste` (
  `id` int(11) NOT NULL COMMENT '主键',
  `sampleno` varchar(50) NOT NULL COMMENT '样品编号',
  `samplename` varchar(500) NOT NULL COMMENT '样品名称',
  `samplemodel` varchar(50) DEFAULT NULL COMMENT '规格型号',
  `samplecount` varchar(100) DEFAULT NULL,
  `storecondition` varchar(50) DEFAULT NULL COMMENT '存储条件',
  `entrustcompany` varchar(200) DEFAULT NULL COMMENT '委托单位',
  `productionunit` varchar(200) DEFAULT NULL COMMENT '生产单位',
  `receivedate` date DEFAULT NULL COMMENT '接收日期',
  `finishdate` date DEFAULT NULL COMMENT '完成日期',
  `senduser` varchar(50) DEFAULT NULL COMMENT '送样人',
  `address` varchar(500) DEFAULT NULL COMMENT '联系地址',
  `relation` varchar(50) DEFAULT NULL COMMENT '联系方式',
  `reporttype` int(1) DEFAULT '0' COMMENT '是否出具检验报告及类型',
  `checkmoney` float(15,2) DEFAULT '0.00' COMMENT '检验费用',
  `payway` int(2) DEFAULT '1',
  `paydes` varchar(200) DEFAULT NULL,
  `prepaymoney` float(15,2) DEFAULT '0.00' COMMENT '预付费用',
  `balancemoney` float(15,2) DEFAULT '0.00' COMMENT '剩余费用',
  `accordingtype` int(1) DEFAULT NULL COMMENT '检验依据类型',
  `accordings` varchar(200) DEFAULT NULL COMMENT '输入的依据内容',
  `samplestatus` varchar(50) DEFAULT NULL COMMENT '样品状态',
  `samplehandle` int(2) DEFAULT NULL,
  `taxtype` int(1) DEFAULT '1' COMMENT '发票类型',
  `billischeck` char(1) DEFAULT 'N' COMMENT '发票是否开具',
  `billno` varchar(50) DEFAULT NULL COMMENT '发票快递单号',
  `reportno` varchar(50) DEFAULT NULL COMMENT '报告快递单号',
  `examineuser` int(5) DEFAULT NULL COMMENT '检验人员',
  `talent` varchar(50) DEFAULT NULL COMMENT '资质',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  `createuser` int(11) DEFAULT NULL COMMENT '录入人',
  `createdate` datetime DEFAULT NULL COMMENT '录入时间',
  `taskdays` int(5) DEFAULT '0' COMMENT '要求完成天数',
  `status` char(1) DEFAULT 'A' COMMENT '状态',
  `statuschangedate` datetime DEFAULT NULL COMMENT '状态变更时间',
  `senduserid` int(255) DEFAULT NULL,
  `email` varchar(1000) DEFAULT NULL,
  `acceptpeople` varchar(100) DEFAULT NULL,
  `resource` varchar(100) DEFAULT NULL,
  `samplecategoryid` int(100) DEFAULT NULL,
  `checkstandardid` int(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sampleregiste
-- ----------------------------

-- ----------------------------
-- Table structure for sendcheckitem
-- ----------------------------
DROP TABLE IF EXISTS `sendcheckitem`;
CREATE TABLE `sendcheckitem` (
  `id` int(11) NOT NULL COMMENT '主键',
  `sendsampleid` int(11) NOT NULL COMMENT '派样单主键',
  `subsendsampleno` varchar(50) DEFAULT NULL COMMENT '派样单子项编号',
  `checkitem` varchar(200) DEFAULT NULL COMMENT '检验项目',
  `type` char(1) DEFAULT '2' COMMENT '检验依据类型',
  `standard` varchar(500) DEFAULT NULL COMMENT '检验依据',
  `instrumentcode` varchar(50) DEFAULT NULL COMMENT '检验仪器代码',
  `checkscope` varchar(50) DEFAULT NULL COMMENT '检出限',
  `unit` varchar(20) DEFAULT NULL COMMENT '检测单位',
  `checkresult` varchar(200) DEFAULT NULL COMMENT '检验结果',
  `labprocess` varchar(11) DEFAULT NULL,
  `balanceaccuracy` varchar(50) DEFAULT NULL,
  `balance` varchar(100) DEFAULT NULL,
  `temperature` varchar(50) DEFAULT NULL,
  `humidity` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sendcheckitem
-- ----------------------------

-- ----------------------------
-- Table structure for sendsample
-- ----------------------------
DROP TABLE IF EXISTS `sendsample`;
CREATE TABLE `sendsample` (
  `id` int(11) NOT NULL COMMENT '主键',
  `sampleno` varchar(50) NOT NULL COMMENT '样品登记单主键',
  `sendsampleno` varchar(50) NOT NULL DEFAULT '' COMMENT '送样单样品编号',
  `sendid` int(11) DEFAULT NULL COMMENT '派样单主键（为多次派样设置）',
  `sendsamplename` varchar(500) NOT NULL DEFAULT '' COMMENT '送样单样品名称',
  `storecondition` varchar(50) DEFAULT NULL COMMENT '存储条件',
  `startdate` date DEFAULT NULL COMMENT '要求开始日期',
  `enddate` date DEFAULT NULL COMMENT '要求完成日期',
  `leader` int(11) DEFAULT NULL COMMENT '派样人',
  `receiveflag` char(1) DEFAULT '0' COMMENT '是否收样',
  `receiveuser` int(11) DEFAULT NULL COMMENT '收样人',
  `receivedate` date DEFAULT NULL COMMENT '收样日期',
  `examineuser` int(5) DEFAULT NULL COMMENT '检验人员',
  `finishflag` char(1) DEFAULT NULL COMMENT '是否检查完成',
  `submitflag` char(1) DEFAULT NULL COMMENT '是否提交完成',
  `finishexaminuser` int(11) DEFAULT NULL COMMENT '实际检查人员',
  `finishdate` date DEFAULT NULL COMMENT '检查完成日期',
  `createuser` int(11) DEFAULT NULL COMMENT '录入人',
  `createdate` date DEFAULT NULL COMMENT '录入时间',
  `status` char(1) DEFAULT 'A' COMMENT '状态',
  `remark` varchar(1000) DEFAULT NULL,
  `samplecount` varchar(100) DEFAULT NULL,
  `statuschangedate` date DEFAULT NULL COMMENT '状态变更时间',
  `receivewarnflag` char(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='派样单';

-- ----------------------------
-- Records of sendsample
-- ----------------------------

-- ----------------------------
-- Table structure for sendsamplenoqrcode
-- ----------------------------
DROP TABLE IF EXISTS `sendsamplenoqrcode`;
CREATE TABLE `sendsamplenoqrcode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subsendsampleno` varchar(255) DEFAULT NULL,
  `qrcodeurl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sendsamplenoqrcode
-- ----------------------------

-- ----------------------------
-- Table structure for subcheckreport
-- ----------------------------
DROP TABLE IF EXISTS `subcheckreport`;
CREATE TABLE `subcheckreport` (
  `id` int(11) NOT NULL COMMENT '主键',
  `checkreportid` int(11) NOT NULL COMMENT '检验报告主键',
  `subsendsampleno` varchar(50) DEFAULT NULL COMMENT '子编号',
  `checkitem` varchar(200) DEFAULT NULL COMMENT '检验项目',
  `standard` varchar(500) DEFAULT NULL COMMENT '检验依据',
  `instrumentcode` varchar(50) DEFAULT NULL COMMENT '检验仪器编号',
  `checkresult` varchar(500) DEFAULT NULL COMMENT '检验结果',
  `type` char(1) DEFAULT NULL COMMENT '依据类型',
  `unit` varchar(20) DEFAULT NULL COMMENT '单位',
  `checkscope` varchar(50) DEFAULT NULL COMMENT '检出限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of subcheckreport
-- ----------------------------

-- ----------------------------
-- Table structure for t_attachment
-- ----------------------------
DROP TABLE IF EXISTS `t_attachment`;
CREATE TABLE `t_attachment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `category` varchar(30) DEFAULT NULL COMMENT '表单类型，指定对应的子表',
  `cid` int(11) DEFAULT NULL COMMENT '表单编号',
  `upload_time` datetime DEFAULT NULL COMMENT '上传时间',
  `name` varchar(50) DEFAULT NULL COMMENT '文件名',
  `url` varchar(100) DEFAULT NULL COMMENT '存储地址',
  `size` int(11) DEFAULT NULL COMMENT '文件大小',
  `describe` varchar(200) DEFAULT NULL COMMENT '文件描述',
  `status` char(1) DEFAULT NULL COMMENT '文件状态',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='附件信息';

-- ----------------------------
-- Records of t_attachment
-- ----------------------------

-- ----------------------------
-- Table structure for t_changepas
-- ----------------------------
DROP TABLE IF EXISTS `t_changepas`;
CREATE TABLE `t_changepas` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `uid` int(11) NOT NULL,
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `status` varchar(2) NOT NULL COMMENT '确认状态',
  `code` varchar(50) NOT NULL COMMENT '随机码',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用户密码修改记录表';

-- ----------------------------
-- Records of t_changepas
-- ----------------------------

-- ----------------------------
-- Table structure for t_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `title` varchar(200) NOT NULL COMMENT '标题',
  `category` varchar(20) NOT NULL COMMENT '栏目',
  `content` text COMMENT '内容',
  `url` varchar(200) DEFAULT NULL COMMENT '外部链接地址',
  `istop` char(1) NOT NULL DEFAULT '0' COMMENT '是否置顶 0否1是',
  `top_time` datetime DEFAULT NULL COMMENT '置顶时间',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态 0 未发布 1已发布',
  `uid` int(11) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='新闻公告表';

-- ----------------------------
-- Records of t_notice
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(20) DEFAULT NULL COMMENT '用户登录名',
  `password` varchar(64) DEFAULT NULL COMMENT '登录密码',
  `authority` varchar(50) DEFAULT NULL COMMENT '用户权限',
  `showname` varchar(20) DEFAULT NULL COMMENT '用户显示名',
  `duty` varchar(50) DEFAULT NULL COMMENT '职务',
  `office` int(11) DEFAULT NULL COMMENT '所属科室',
  `email` varchar(20) DEFAULT NULL COMMENT '电子邮箱',
  `groupid` int(11) DEFAULT NULL COMMENT '所属组',
  `telphone` varchar(15) DEFAULT NULL COMMENT '联系电话',
  `address` varchar(100) DEFAULT NULL COMMENT '联系地址',
  `lastlogin` datetime DEFAULT NULL COMMENT '上次登录时间',
  `lastip` varchar(20) DEFAULT NULL COMMENT '上次登录IP',
  `login_times` int(11) DEFAULT '0' COMMENT '登录次数',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `sysname` (`name`)
) ENGINE=MyISAM AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('49', 'linnan', '7a2fbb3ab564038c162a2bc16216f050', 'C', '林楠', '实验员', '4', null, '4', null, null, '2019-01-22 21:33:35', '0:0:0:0:0:0:0:1', '91');
INSERT INTO `t_user` VALUES ('2', 'admin', '7a2fbb3ab564038c162a2bc16216f050', 'A', 'admin', '管理员', '1', null, '1', null, null, '2019-01-24 15:07:13', '0:0:0:0:0:0:0:1', '877');
INSERT INTO `t_user` VALUES ('3', 'hejunzhi', '7a2fbb3ab564038c162a2bc16216f050', 'C', '贺俊智', '实验员', '3', null, '4', null, null, '2018-12-03 14:08:46', '0:0:0:0:0:0:0:1', '50');
INSERT INTO `t_user` VALUES ('4', 'huangwenwen', '7a2fbb3ab564038c162a2bc16216f050', 'C', '黄雯雯', '实验员', '2', null, '4', null, null, '2019-01-24 15:24:54', '0:0:0:0:0:0:0:1', '3');
INSERT INTO `t_user` VALUES ('5', 'donghaifeng', '7a2fbb3ab564038c162a2bc16216f050', 'C', '董海峰', '实验员', '2', null, '4', null, null, null, null, '0');
INSERT INTO `t_user` VALUES ('6', 'shenshangyi', '7a2fbb3ab564038c162a2bc16216f050', 'C', '沈上圯', '实验员', '2', null, '4', null, null, '2019-01-24 15:24:12', '0:0:0:0:0:0:0:1', '210');
INSERT INTO `t_user` VALUES ('7', 'weixiaoxiao', '7a2fbb3ab564038c162a2bc16216f050', 'C', '魏晓晓', '实验员', '2', null, '4', null, null, null, null, '0');
INSERT INTO `t_user` VALUES ('8', 'chihaitao', '7a2fbb3ab564038c162a2bc16216f050', 'C', '池海涛', '实验员', '2', null, '4', null, null, '2019-01-24 15:30:53', '0:0:0:0:0:0:0:1', '2');
INSERT INTO `t_user` VALUES ('9', 'leshengfeng', '7a2fbb3ab564038c162a2bc16216f050', 'C', '乐胜锋', '实验员', '4', null, '4', null, null, null, null, '0');
INSERT INTO `t_user` VALUES ('10', 'zhoumingqiang', '7a2fbb3ab564038c162a2bc16216f050', 'B', '周明强', '主任', '3', null, '2', null, null, '2018-11-13 19:36:37', '0:0:0:0:0:0:0:1', '2');
INSERT INTO `t_user` VALUES ('11', 'huangwei', '7a2fbb3ab564038c162a2bc16216f050', 'C', '黄伟', '实验员', '3', null, '4', null, null, null, null, '0');
INSERT INTO `t_user` VALUES ('12', 'wenjing', '7a2fbb3ab564038c162a2bc16216f050', 'C', '温静', '实验员', '3', null, '4', null, null, null, null, '0');
INSERT INTO `t_user` VALUES ('13', 'liuna', '7a2fbb3ab564038c162a2bc16216f050', 'C', '刘娜', '实验员', '3', null, '4', null, null, '2015-03-22 00:26:48', '127.0.0.1', '4');
INSERT INTO `t_user` VALUES ('14', 'zhaoting', '7a2fbb3ab564038c162a2bc16216f050', 'C', '赵婷', '实验员', '3', null, '4', null, null, null, null, '0');
INSERT INTO `t_user` VALUES ('15', 'liurui', '7a2fbb3ab564038c162a2bc16216f050', 'C', '刘蕊', '实验员', '3', null, '4', null, null, '2015-03-17 23:05:28', '127.0.0.1', '9');
INSERT INTO `t_user` VALUES ('16', 'mabokai', '7a2fbb3ab564038c162a2bc16216f050', 'C', '马博凯', '实验员', '3', null, '4', null, null, '2015-03-15 23:48:36', '127.0.0.1', '14');
INSERT INTO `t_user` VALUES ('17', 'shiyingjie', '7a2fbb3ab564038c162a2bc16216f050', 'C', '史迎杰', '实验员', '3', null, '4', null, null, '2018-12-03 14:18:16', '0:0:0:0:0:0:0:1', '3');
INSERT INTO `t_user` VALUES ('18', 'gouxinlei', '7a2fbb3ab564038c162a2bc16216f050', 'C', '勾新磊', '实验员', '3', null, '4', null, null, null, null, '0');
INSERT INTO `t_user` VALUES ('19', 'wangjing', '7a2fbb3ab564038c162a2bc16216f050', '|B|C|', '王静', '接样员', '3', null, '3', null, null, '2018-12-03 14:35:50', '0:0:0:0:0:0:0:1', '11');
INSERT INTO `t_user` VALUES ('20', 'duanyan', '7a2fbb3ab564038c162a2bc16216f050', '|B|C|', '段艳', '接样员', '2', null, '3', null, null, '2019-01-24 15:32:39', '0:0:0:0:0:0:0:1', '57');
INSERT INTO `t_user` VALUES ('40', 'liuweili', '7a2fbb3ab564038c162a2bc16216f050', 'A', '刘伟丽', '部长', '1', null, '1', null, null, null, null, '0');
INSERT INTO `t_user` VALUES ('41', 'huguanghui', '7a2fbb3ab564038c162a2bc16216f050', 'D', '胡光辉', '部门主管', '1', null, '1', null, null, '2015-03-24 23:25:27', '127.0.0.1', '2');
INSERT INTO `t_user` VALUES ('42', 'zhaoxinying', '7a2fbb3ab564038c162a2bc16216f050', 'D', '赵新颖', '部门主管', '1', null, '1', null, null, '2015-04-15 23:48:58', '127.0.0.1', '267');
INSERT INTO `t_user` VALUES ('43', 'zhangmei', '7a2fbb3ab564038c162a2bc16216f050', 'B', '张梅', '主任', '2', null, '2', null, null, null, null, '0');
INSERT INTO `t_user` VALUES ('44', 'zhouxiaojing', '7a2fbb3ab564038c162a2bc16216f050', 'B', '周晓晶', '主任', '4', null, '2', null, null, null, null, '0');
INSERT INTO `t_user` VALUES ('45', 'duning', '7a2fbb3ab564038c162a2bc16216f050', 'B', '杜宁', '接样员', '4', null, '3', null, null, null, null, '0');
INSERT INTO `t_user` VALUES ('46', 'lichen', '7a2fbb3ab564038c162a2bc16216f050', 'C', '李晨', '实验员', '3', null, '4', null, null, null, null, '0');
INSERT INTO `t_user` VALUES ('47', 'liqinmei', '7a2fbb3ab564038c162a2bc16216f050', 'C', '李琴梅', '实验员', '2', null, '4', null, null, '2018-11-29 14:10:45', '0:0:0:0:0:0:0:1', '8');
INSERT INTO `t_user` VALUES ('48', 'wangwei', '7a2fbb3ab564038c162a2bc16216f050', 'C', '王尉', '实验员', '4', null, '4', null, null, null, null, '0');
INSERT INTO `t_user` VALUES ('50', 'liuyiren', '7a2fbb3ab564038c162a2bc16216f050', 'C', '刘奕忍', '实验员', '2', null, '4', null, null, '2015-03-16 22:14:31', null, '0');
INSERT INTO `t_user` VALUES ('1000', 'qita', '7a2fbb3ab564038c162a2bc16216f050', 'C', '其他', '实验员', '1', null, '4', null, null, null, null, '0');
INSERT INTO `t_user` VALUES ('1002', 'zzq', '7a2fbb3ab564038c162a2bc16216f050', 'C', 'zzq', '实验员', '3', null, '0', '18210915622', null, null, null, '0');

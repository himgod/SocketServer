/*
Navicat MySQL Data Transfer

Source Server         : localhost_3307
Source Server Version : 50151
Source Host           : localhost:3307
Source Database       : faxserver

Target Server Type    : MYSQL
Target Server Version : 50151
File Encoding         : 65001

Date: 2014-10-25 15:46:22
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `tdbi_faxout`
-- ----------------------------
DROP TABLE IF EXISTS `tdbi_faxout`;
CREATE TABLE `tdbi_faxout` (
  `TASK_ID` varchar(100) NOT NULL,
  `MOULDINGNAME` varchar(40) NOT NULL,
  `FAXID` varchar(255) NOT NULL,
  `FAXTASKTYPE` varchar(255) DEFAULT NULL,
  `FAXCREATEDATETIME` varchar(40) DEFAULT NULL,
  `FAXNUMBER` varchar(255) NOT NULL,
  `FAXRETRYINTERVAL` varchar(255) DEFAULT NULL,
  `FAXENDTIME` varchar(40) DEFAULT NULL,
  `ERRORMSG` varchar(255) DEFAULT NULL,
  `FAXBEGINTIME` varchar(255) DEFAULT NULL,
  `FAXSTATUS` varchar(255) DEFAULT NULL,
  `FAXPRI` varchar(255) DEFAULT NULL,
  `FAXRETRYTIMES` varchar(255) DEFAULT NULL,
  `FAXSUBJECT` varchar(255) DEFAULT NULL,
  `FAXRECEIVER` varchar(255) DEFAULT NULL,
  `FAXRECEIVERCOMPANY` varchar(255) DEFAULT NULL,
  `FAXSCHEDULE` varchar(255) DEFAULT NULL,
  `FAXFAILEDCOUNT` varchar(255) DEFAULT NULL,
  `FAXDURATION` varchar(255) DEFAULT NULL,
  `FAXPAGES` varchar(255) DEFAULT NULL,
  `FAXSENTPAGES` varchar(255) DEFAULT NULL,
  `ERRORCODE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`TASK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of tdbi_faxout
-- ----------------------------
INSERT INTO `tdbi_faxout` VALUES ('03C0B7961DFB445291AAA7C64247E946', '330000', '03C0B7961DFB445291AAA7C64247E946_20141024050433.fax.data', '0', '2014-10-24 17:03:59', '12345', '3', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tdbi_faxout` VALUES ('0C328C0EB6C744359941548646B16B8A', '330000', '0C328C0EB6C744359941548646B16B8A_20141024050057.fax.data', '0', '2014-10-24 17:00:23', '12345', '3', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tdbi_faxout` VALUES ('27800', '500023', '27800_20141024112501.fax.data', '1', '2011-8-30 8:28', '87339835', '60', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tdbi_faxout` VALUES ('2780000', '500023', '2780000_20141025025537.fax.data', '1', '2011-8-30 8:28', '87339835', '60', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tdbi_faxout` VALUES ('278000545', '500023', '278000545_20141025032250.fax.data', '1', '2011-8-30 8:28', '87339835', '60', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tdbi_faxout` VALUES ('27800055', '500023', '27800055_20141025025810.fax.data', '1', '2011-8-30 8:28', '87339835', '60', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tdbi_faxout` VALUES ('278000555', '500023', '278000555_20141025032619.fax.data', '1', '2011-8-30 8:28', '87339835', '60', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tdbi_faxout` VALUES ('2780006666', '500023', '2780006666_20141025032929.fax.data', '1', '2011-8-30 8:28', '87339835', '60', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tdbi_faxout` VALUES ('2780006667', '500023', '2780006667_20141025033024.fax.data', '1', '2011-8-30 8:28', '87339835', '60', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tdbi_faxout` VALUES ('27801', '500023', '27801_20141024112554.fax.data', '1', '2011-8-30 8:28', '87339835', '60', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tdbi_faxout` VALUES ('27802', '500023', '27802_20141024112603.fax.data', '1', '2011-8-30 8:28', '87339835', '60', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tdbi_faxout` VALUES ('27804', '500023', '27804_20141024113225.fax.data', '1', '2011-8-30 8:28', '87339835', '60', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tdbi_faxout` VALUES ('27805', '500023', '27805_20141024113631.fax.data', '1', '2011-8-30 8:28', '87339835', '60', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tdbi_faxout` VALUES ('27806', '500023', '27806_20141024113642.fax.data', '1', '2011-8-30 8:28', '87339835', '60', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tdbi_faxout` VALUES ('2780B1EEEF804151BE934987272E57EC', '330000', '2780B1EEEF804151BE934987272E57EC_20141024051338.fax.data', '0', '2014-10-24 17:13:04', '12345', '3', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tdbi_faxout` VALUES ('27874', '500023', '27874_20141024083442.fax.data0', '1', '2011-8-30 8:28', '87339835', '60', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tdbi_faxout` VALUES ('27875', '500023', '27875_20141024090502.fax.data0', '1', '2011-8-30 8:28', '87339835', '60', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tdbi_faxout` VALUES ('27876', '500023', '27876_20141024100841.fax.data0', '1', '2011-8-30 8:28', '87339835', '60', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tdbi_faxout` VALUES ('27877', '500023', '27877_20141024104124.fax.data', '1', '2011-8-30 8:28', '87339835', '60', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tdbi_faxout` VALUES ('38B2AC3298174026BB580B382E938ED7', '330000', '38B2AC3298174026BB580B382E938ED7_20141024051404.fax.data', '0', '2014-10-24 17:13:29', '12345', '3', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tdbi_faxout` VALUES ('6010AA25E74C4F889ECDBFD3A453D37A', '330000', '6010AA25E74C4F889ECDBFD3A453D37A_20141024045349.fax.data', '0', '2014-10-24 16:53:15', '12345', '3', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tdbi_faxout` VALUES ('879CC42F0EDC48FCBF46488CA05DAE74', '330000', '879CC42F0EDC48FCBF46488CA05DAE74_20141024051037.fax.data', '0', '2014-10-24 17:10:02', '12345', '3', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tdbi_faxout` VALUES ('A77F06443A1749C59B06757ECBAA16A2', '330000', 'A77F06443A1749C59B06757ECBAA16A2_20141024051242.fax.data', '0', '2014-10-24 17:12:08', '12345', '3', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tdbi_faxout` VALUES ('C9AB743162F94EF4BDAD29DD0CAE55C0', '330000', 'C9AB743162F94EF4BDAD29DD0CAE55C0_20141024052227.fax.data', '0', '2014-10-24 17:21:53', '12345', '3', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tdbi_faxout` VALUES ('EBF37D6696094D2F96A72FBE4875B908', '330000', 'EBF37D6696094D2F96A72FBE4875B908_20141024050548.fax.data', '0', '2014-10-24 17:05:13', '12345', '3', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tdbi_faxout` VALUES ('F0CCCEF3DE8246D3ACAFBACA9A078F9F', '330000', 'F0CCCEF3DE8246D3ACAFBACA9A078F9F_20141024045259.fax.data', '0', '2014-10-24 16:52:24', '12345', '3', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tdbi_faxout` VALUES ('F5EFB2CBACA446E68CDA56B5147E70A5', '330000', 'F5EFB2CBACA446E68CDA56B5147E70A5_20141024045618.fax.data', '0', '2014-10-24 16:55:44', '12345', '3', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

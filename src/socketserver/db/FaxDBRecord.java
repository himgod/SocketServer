package socketserver.db;


public class FaxDBRecord
{
		private String taskID;//任务ID
		private String moduleName;//模块名称
		private String faxID;//传真ID，表示文件名称
		private String faxTaskType;//传真任务类型
		private String faxCreateDateTime;//创建时间
		private String faxSubject;//传真主题
		private String faxNumber;//传真号码
		private String faxReceiver;//传真接收人
		private String faxReceiverCompany;
		private String faxPRI;//传真中继通道号
		private String faxSendDateTime;//传真发送完成时间
		private String faxSchedule;//传真清单
		private String faxRetryTimes; //传真重发次数
		private String faxRetryInterval;//传真重发间隔
		private String faxStatus; //传真状态
		private String faxFailedCount;//传真失败次数
		private String faxBeginTime;//传真开始发送时间
		private String faxEndTime;//传真发送结束时间
		private String faxDuration;//传真持续时间
		private String faxPages; //传真页数
		private String faxSentPages;//传真发送的页数
		private String errorMsg;//传真错误消息
		private String errorCode;//传真错误代码
		public String getTaskID() {
			return taskID;
		}
		public void setTaskID(String taskID) {
			this.taskID = taskID;
		}
		public String getModuleName() {
			return moduleName;
		}
		public void setModuleName(String moduleName) {
			this.moduleName = moduleName;
		}
		public String getFaxID() {
			return faxID;
		}
		public void setFaxID(String faxID) {
			this.faxID = faxID;
		}
		public String getFaxTaskType() {
			return faxTaskType;
		}
		public void setFaxTaskType(String faxTaskType) {
			this.faxTaskType = faxTaskType;
		}
		public String getFaxCreateDateTime() {
			return faxCreateDateTime;
		}
		public void setFaxCreateDateTime(String faxCreateDateTime) {
			this.faxCreateDateTime = faxCreateDateTime;
		}
		public String getFaxSubject() {
			return faxSubject;
		}
		public void setFaxSubject(String faxSubject) {
			this.faxSubject = faxSubject;
		}
		public String getFaxNumber() {
			return faxNumber;
		}
		public void setFaxNumber(String faxNumber) {
			this.faxNumber = faxNumber;
		}
		public String getFaxReceiver()
		{
			return faxReceiver;
		}
		public void setFaxReceiver(String faxReceiver)
		{
			this.faxReceiver = faxReceiver;
		}
		public String getFaxReceiverCompany() 
		{
			return faxReceiverCompany;
		}
		public void setFaxReceiverCompany(String faxReceiverCompany)
		{
			this.faxReceiverCompany = faxReceiverCompany;
		}
		public String getFaxPRI()
		{
			return faxPRI;
		}
		public void setFaxPRI(String faxPRI)
		{
			this.faxPRI = faxPRI;
		}
		public String getFaxSendDateTime() 
		{
			return faxSendDateTime;
		}
		public void setFaxSendDateTime(String faxSendDateTime)
		{
			this.faxSendDateTime = faxSendDateTime;
		}
		public String getFaxSchedule() 
		{
			return faxSchedule;
		}
		public void setFaxSchedule(String faxSchedule) 
		{
			this.faxSchedule = faxSchedule;
		}
		public String getFaxRetryTimes()
		{
			return faxRetryTimes;
		}
		public void setFaxRetryTimes(String faxRetryTimes)
		{
			this.faxRetryTimes = faxRetryTimes;
		}
		public String getFaxRetryInterval() 
		{
			return faxRetryInterval;
		}
		public void setFaxRetryInterval(String faxRetryInterval) 
		{
			this.faxRetryInterval = faxRetryInterval;
		}
		public String getFaxStatus() {
			return faxStatus;
		}
		public void setFaxStatus(String faxStatus) 
		{
			this.faxStatus = faxStatus;
		}
		public String getFaxFailedCount()
		{
			return faxFailedCount;
		}
		public void setFaxFailedCount(String faxFailedCount)
		{
			this.faxFailedCount = faxFailedCount;
		}
		public String getFaxBeginTime() 
		{
			return faxBeginTime;
		}
		public void setFaxBeginTime(String faxBeginTime)
		{
			this.faxBeginTime = faxBeginTime;
		}
		public String getFaxEndTime() {
			return faxEndTime;
		}
		public void setFaxEndTime(String faxEndTime) {
			this.faxEndTime = faxEndTime;
		}
		public String getFaxDuration() 
		{
			return faxDuration;
		}
		public void setFaxDuration(String faxDuration) 
		{
			this.faxDuration = faxDuration;
		}
		public String getFaxPages()
		{
			return faxPages;
		}
		public void setFaxPages(String faxPages)
		{
			this.faxPages = faxPages;
		}
		public String getFaxSentPages()
		{
			return faxSentPages;
		}
		public void setFaxSentPages(String faxSentPages)
		{
			this.faxSentPages = faxSentPages;
		}
		public String getErrorMsg()
		{
			return errorMsg;
		}
		public void setErrorMsg(String errorMsg)
		{
			this.errorMsg = errorMsg;
		}
		public String getErrorCode()
		{
			return errorCode;
		}
		public void setErrorCode(String errorCode)
		{
			this.errorCode = errorCode;
		}
		
}



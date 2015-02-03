package socketserver.db;


public class FaxDBRecord
{
		private String taskID;//����ID
		private String moduleName;//ģ������
		private String faxID;//����ID����ʾ�ļ�����
		private String faxTaskType;//������������
		private String faxCreateDateTime;//����ʱ��
		private String faxSubject;//��������
		private String faxNumber;//�������
		private String faxReceiver;//���������
		private String faxReceiverCompany;
		private String faxPRI;//�����м�ͨ����
		private String faxSendDateTime;//���淢�����ʱ��
		private String faxSchedule;//�����嵥
		private String faxRetryTimes; //�����ط�����
		private String faxRetryInterval;//�����ط����
		private String faxStatus; //����״̬
		private String faxFailedCount;//����ʧ�ܴ���
		private String faxBeginTime;//���濪ʼ����ʱ��
		private String faxEndTime;//���淢�ͽ���ʱ��
		private String faxDuration;//�������ʱ��
		private String faxPages; //����ҳ��
		private String faxSentPages;//���淢�͵�ҳ��
		private String errorMsg;//���������Ϣ
		private String errorCode;//����������
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



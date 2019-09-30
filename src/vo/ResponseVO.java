package vo;

public class ResponseVO {
	private boolean result;
	private String msg;
	private String menu;
	
	public ResponseVO() {
		//생성자	
	}
	
	public ResponseVO(boolean result, String msg) {
		super();
		this.result = result;
		this.msg = msg;
	}

	public ResponseVO(boolean result, String msg, String menu) {
		super();
		this.result = result;
		this.msg = msg;
		this.menu = menu;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}
	
}

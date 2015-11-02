package ip.availability;

public class Stdin {
	private String command;
	
	public Stdin(String cmd) {
		this.command = cmd;
	}
	
	public String get_cmd() {
		return this.command;
	}
	
	public String get_name() {
		return this.split_cmd()[0];
	}
	
	public String get_command() {
		return this.split_cmd()[1];
	}
	
	public String get_param() {
		return this.split_cmd()[2];
	}
	
	private String[] split_cmd() {
		return this.command.split(":");
	}
	
}
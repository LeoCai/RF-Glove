package dislab.rfidaction.command;

import dislab.rfidaction.core.CommandInfo;
import dislab.rfidaction.core.CommandType;

/**
 * 命令，抽象类型
 *
 */
public abstract class Command {

	protected CommandType commandType;
	protected CommandInfo commandinfo;
	public String name;

	public void setCommandType(CommandType commandType) {
		this.commandType = commandType;
	}

	public CommandType getCommandType() {
		// TODO Auto-generated method stub
		return this.commandType;
	}

	abstract public void excute();
	
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}

	public String getName() {
		return name;
	}
}

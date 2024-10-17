package tags;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;

public class VarUsuariosTagTEI extends TagExtraInfo {

	public VariableInfo[] getVariableInfo(TagData data) {
		return new VariableInfo[] { new VariableInfo("usuario", "java.lang.String", true, VariableInfo.NESTED),
				new VariableInfo("email", "java.lang.String", true, VariableInfo.NESTED)};
	}
}

package application;

public class decl_info {
	private Byte name;
	private Object decl_string;
	private Object ;
	
	public decl_info(Byte name, Object decl_string, Object ) {
		setName(name);
		setDecl_string(decl_string);
		set();
	}
	public decl_info() {
	}
	
	public void decl_info_free() {
		Object generatedParams = this.getParams();
		Byte generatedName = this.getName();
		if (decl) {
			for (size_t i = 0;
			 i < generatedParams.getNum(); i++) {
				generatedParams.getArray() + i.decl_param_free();
			}
			.da_free(generatedParams);
			ModernizedCProgram.bfree(generatedName);
			.memset(decl, 0, );
		} 
	}
	public Object name_exists(Object name) {
		Object generatedParams = this.getParams();
		for (size_t i = 0;
		 i < generatedParams.getNum(); i++) {
			byte param_name = generatedParams.getArray()[i].getName();
			if (.strcmp(name, param_name) == 0) {
				return true;
			} 
		}
		return false;
	}
	public int parse_param(Object cfp) {
		strref ref = new strref();
		int code;
		decl_param param = new decl_param(0);
		code = ModernizedCProgram.cf_next_name_ref(cfp, ref, "type or storage specifier", /* get storage specifiers */",");
		if (code != PARSE_SUCCESS) {
			return code;
		} 
		Object generatedFlags = param.getFlags();
		while (ModernizedCProgram.is_in_out_specifier(cfp, ref, generatedFlags)) {
			code = ModernizedCProgram.cf_next_name_ref(cfp, ref, "type or storage specifier", ",");
			if (code != PARSE_SUCCESS) {
				return code;
			} 
		}
		if (generatedFlags == /* parameters not marked with specifiers are input parameters */0) {
			param.setFlags((1 << 0));
		} 
		call_param_type generatedType = param.getType();
		if (!ModernizedCProgram.get_type(ref, generatedType, false)) {
			cfp.cf_adderror_expecting("type");
			cfp.cf_go_to_token(",", ")");
			return PARSE_CONTINUE;
		} 
		Byte generatedName = param.getName();
		code = cfp.cf_next_name(generatedName, "parameter name", /* name */",");
		if (code != PARSE_SUCCESS) {
			return code;
		} 
		if (decl.name_exists(generatedName)) {
			ModernizedCProgram.err_existing_name(cfp, generatedName);
		} 
		if (ModernizedCProgram.is_reserved_name(generatedName)) {
			ModernizedCProgram.err_reserved_name(cfp, generatedName);
		} 
		Object generatedParams = this.getParams();
		.da_push_back(generatedParams, param);
		return PARSE_SUCCESS;
	}
	public Object parse_decl_string(Object decl_string) {
		cf_parser cfp = new cf_parser();
		strref ret_type = new strref();
		decl_param ret_param = new decl_param(0);
		int code;
		 success = new ();
		this.setDecl_string(decl_string);
		ret_param.setFlags((1 << 1));
		cfp.cf_parser_init();
		if (!cfp.cf_parser_parse(decl_string, "declaration")) {
			;
		} 
		code = ModernizedCProgram.cf_get_name_ref(cfp, ret_type, "return type", ((Object)0));
		if (code == PARSE_EOF) {
			;
		} 
		call_param_type generatedType = ret_param.getType();
		if (!ModernizedCProgram.get_type(ret_type, generatedType, true)) {
			cfp.cf_adderror_expecting("return type");
		} 
		Byte generatedName = this.getName();
		code = cfp.cf_next_name(generatedName, "function name", "(");
		if (code == PARSE_EOF) {
			;
		} 
		if (ModernizedCProgram.is_reserved_name(generatedName)) {
			ModernizedCProgram.err_reserved_name(cfp, generatedName);
		} 
		code = cfp.cf_next_token_should_be("(", "(", ((Object)0));
		if (code == PARSE_EOF) {
			;
		} 
		ModernizedCProgram.parse_params(cfp, decl);
		Object generatedParams = this.getParams();
		if (success && generatedType != call_param_type.CALL_PARAM_TYPE_VOID) {
			ret_param.setName(ModernizedCProgram.bstrdup("return"));
			.da_push_back(generatedParams, ret_param);
		} 
		if (!success) {
			decl.decl_info_free();
		} 
		ModernizedCProgram.print_errors(cfp, decl_string);
		cfp.cf_parser_free();
		return success;
	}
	public Byte getName() {
		return name;
	}
	public void setName(Byte newName) {
		name = newName;
	}
	public Object getDecl_string() {
		return decl_string;
	}
	public void setDecl_string(Object newDecl_string) {
		decl_string = newDecl_string;
	}
	public Object get() {
		return ;
	}
	public void set(Object new) {
		 = new;
	}
}

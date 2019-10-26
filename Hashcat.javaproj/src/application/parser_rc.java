package application;

public enum parser_rc {
	PARSER_OK, 
	PARSER_COMMENT, 
	PARSER_GLOBAL_ZERO, 
	PARSER_GLOBAL_LENGTH, 
	PARSER_HASH_LENGTH, 
	PARSER_HASH_VALUE, 
	PARSER_SALT_LENGTH, 
	PARSER_SALT_VALUE, 
	PARSER_SALT_ITERATION, 
	PARSER_SEPARATOR_UNMATCHED, 
	PARSER_SIGNATURE_UNMATCHED, 
	PARSER_HCCAPX_FILE_SIZE, 
	PARSER_HCCAPX_EAPOL_LEN, 
	PARSER_PSAFE2_FILE_SIZE, 
	PARSER_PSAFE3_FILE_SIZE, 
	PARSER_TC_FILE_SIZE, 
	PARSER_VC_FILE_SIZE, 
	PARSER_SIP_AUTH_DIRECTIVE, 
	PARSER_HASH_FILE, 
	PARSER_HASH_ENCODING, 
	PARSER_SALT_ENCODING, 
	PARSER_LUKS_FILE_SIZE, 
	PARSER_LUKS_MAGIC, 
	PARSER_LUKS_VERSION, 
	PARSER_LUKS_CIPHER_TYPE, 
	PARSER_LUKS_CIPHER_MODE, 
	PARSER_LUKS_HASH_TYPE, 
	PARSER_LUKS_KEY_SIZE, 
	PARSER_LUKS_KEY_DISABLED, 
	PARSER_LUKS_KEY_STRIPES, 
	PARSER_LUKS_HASH_CIPHER, 
	PARSER_HCCAPX_SIGNATURE, 
	PARSER_HCCAPX_VERSION, 
	PARSER_HCCAPX_MESSAGE_PAIR, 
	PARSER_TOKEN_ENCODING, 
	PARSER_TOKEN_LENGTH, 
	PARSER_INSUFFICIENT_ENTROPY, 
	PARSER_PKZIP_CT_UNMATCHED, 
	PARSER_UNKNOWN_ERROR
}
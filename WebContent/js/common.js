function _change() {
	$("#vCode").attr("src", "/dormitory/VerifyCodeServlet?" + new Date().getTime());
}
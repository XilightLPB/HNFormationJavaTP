module EMMBank {
	requires java.base;
	requires java.xml;
	requires com.fasterxml.jackson.annotation;
	requires com.fasterxml.jackson.datatype.jsr310;
	
	opens components;
}
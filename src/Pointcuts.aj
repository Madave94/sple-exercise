
public aspect Pointcuts {
	// a) execution of Client send(text)
	pointcut a() : execution(void client.Client.send(*));
	// b) invocation of method send
	pointcut b() : call(void *.send(..));
}

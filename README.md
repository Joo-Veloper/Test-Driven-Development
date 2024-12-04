assertEquals(expected, actual) : 실제 값이 기대하는 값과 같은지 검사.
assertNotEquals(unexpected, actual) : 실제 값이 특정값과 같지 않은지 검사.
assertSame(Object expected, Object actual) 두 객체가 동일한 객체인지 검사.
assertNotSame(Object unexpected, Object actual)  두 객체가 동일하지 않은 객체인지 검사
assertTrue(boolean condition) 값이 True 인지 검사
assertFalse(boolean condition) 값이 False 인지 검사
assertNull(Object actual) 값이 null 인지 검사
assertNotNull(Object actual) 값이 null 이 아닌지 검사
fail() 테스트를 실패 처리
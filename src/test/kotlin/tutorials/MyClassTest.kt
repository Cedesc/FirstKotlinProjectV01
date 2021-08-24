package tutorials

import org.junit.Assert.*

internal class MyClassTest {

    @org.junit.Test
    fun foo() {
        var obj = MyClass()
        var res = obj.foo(1)
        assertEquals(res, 43)
    }
}

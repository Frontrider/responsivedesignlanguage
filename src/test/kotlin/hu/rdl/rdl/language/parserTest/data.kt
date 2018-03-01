package hu.rdl.rdl.language.parserTest

const val file =
        """
                <doc>
                <l>
                <c:100px:200px:10px>card1 this text should be extracted out of the system as is.</>
                <c:100px:200px>card1.2</>
                <c:100px:200px>card1.3</>
                /*<c:100w:200px:10px>cardwide</>*/
                </>
                <l>
                <c:1/2:100px></>
                <c:1/2:150px::></>
                </>
                </>
                """
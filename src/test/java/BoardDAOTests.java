import org.junit.Test;
import org.zerock.dao.BoardDAO;
import org.zerock.domain.BoardVO;
import org.zerock.domain.PageDTO;
import org.zerock.domain.PageMaker;

import static junit.framework.TestCase.assertNotNull;

public class BoardDAOTests {

    private BoardDAO boardDAO = new BoardDAO();

    @Test
    public void testRead() throws Exception{
        int bno = 5701646;
        System.out.println(boardDAO.getBoard(bno,true));
    }

    @Test
    public void testPageMaker(){
        PageDTO dto = PageDTO.of().setPage(7).setSize(10);
        int total =123;
        PageMaker pageMaker = new PageMaker(total,dto);
    }

    @Test
    public void testList()throws Exception{
        boardDAO.getList(PageDTO.of().setPage(2).setSize(100))
                .forEach(vo-> System.out.println(vo));
    }

    @Test
    public void testInsert()throws Exception{
        BoardVO vo = new BoardVO();
        vo.setTitle("헤이모두들안녕");
        vo.setContent("내가누군지아니");
        vo.setWriter("jiin");
        boardDAO.create(vo);
    }




    @Test
    public void test1(){

        assertNotNull(boardDAO); // not null이면 성공할수 없다는 것.
        System.out.println("test1");

        PageDTO pageDTO = PageDTO.of().setSize(20).setPage(5);
        System.out.println(pageDTO);
    }
}

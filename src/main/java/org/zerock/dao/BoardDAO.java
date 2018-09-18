package org.zerock.dao;

import org.zerock.domain.BoardVO;
import org.zerock.domain.PageDTO;

import java.util.ArrayList;
import java.util.List;

public class BoardDAO {

    private static final String INSERT="insert into tbl_board (BNO, TITLE, CONTENT, WRITER) " +
            "values(seq_board.nextval,?,?,?)";

    private static  final String LIST = "select *\n" +
            "from (select\n" +
            "             /*+ INDEX_DESC (tbl_board pk_board) */\n" +
            "          ROWNUM rn, bno, title, content, WRITER, REGDATE, UPDATEDATE, viewcnt \n" +
            "      from TBL_BOARD\n" +
            "      where bno > 0\n" +
            "        and ROWNUM <= (? * ?))\n" +
            "where rn > (?-1) * ?";

    private static final String UPDATE_HIT = "update tbl_board set viewcnt = viewcnt + 1 where bno = ?";

    private static final String READ = "select * from tbl_board where bno = ?";

    public BoardVO getBoard(Integer bno, boolean updateable) throws Exception{
        BoardVO vo = new BoardVO();

        new QueryExecutor() {
            @Override
            public void doJob() throws Exception {
                if(updateable){
                    stmt = con.prepareStatement(UPDATE_HIT);
                    stmt.setInt(1,bno);
                    stmt.executeUpdate();
                    stmt.close();
                }
                stmt = con.prepareStatement(READ);
                stmt.setInt(1,bno);
                rs = stmt.executeQuery();
                while(rs.next()){
                    vo.setBno(rs.getInt("bno"));
                    vo.setTitle(rs.getString("title"));
                    vo.setContent(rs.getString("content"));
                    vo.setViewcnt(rs.getInt("viewcnt"));
                    vo.setWriter(rs.getString("writer"));
                    vo.setRegdate(rs.getDate("regdate"));
                    vo.setUpdatedate(rs.getDate("updatedate"));

                }
            }
        }.executeAll();

        return vo;
    }


    public List<BoardVO> getList(PageDTO pageDTO)throws Exception{
        List<BoardVO> list = new ArrayList<>();

        new QueryExecutor() {
            @Override
            public void doJob() throws Exception {
                stmt = con.prepareStatement(LIST);
                int i = 1;
                stmt.setInt(i++,pageDTO.getPage());
                stmt.setInt(i++,pageDTO.getSize());
                stmt.setInt(i++,pageDTO.getPage());
                stmt.setInt(i++,pageDTO.getSize());
                rs = stmt.executeQuery();

                while(rs.next()){
                    BoardVO vo = new BoardVO();
                    int idx = 2;

                    //컬럼명보다 인덱스번호가 빠르다.
                    vo.setBno(rs.getInt(idx++));
                    vo.setTitle(rs.getString(idx++));
                    vo.setContent(rs.getString(idx++));
                    vo.setWriter(rs.getString(idx++));
                    vo.setRegdate(rs.getDate(idx++));
                    vo.setUpdatedate(rs.getDate(idx++));
                    vo.setViewcnt(rs.getInt(idx++));
                    list.add(vo);
                }

            }
        }.executeAll();

        return list;
    }

    public void create(BoardVO vo)throws Exception{

        new QueryExecutor() {
            @Override
            public void doJob() throws Exception {
                stmt = con.prepareStatement(INSERT);
                int i = 1;
                stmt.setString(i++, vo.getTitle());
                stmt.setString(i++, vo.getContent());
                stmt.setString(i++, vo.getWriter());
                stmt.executeUpdate();
            }
        }.executeAll();

    }

    //modify
    public void modifyContent(final BoardVO vo){

        final String sql = "update tbl_board set title =?, content =? where bno =?";

        new QueryExecutor() {
            @Override
            public void doJob() throws Exception {
                stmt = con.prepareStatement(sql);
                stmt.setString(1, vo.getTitle());
                stmt.setString(2, vo.getContent());
                stmt.setInt(3, vo.getBno());
                stmt.executeUpdate();
            }
        }.executeAll();
    }
    //remove
    public void removeContent(final int bno){
        final String sql = "delete from tbl_board where bno = ?";

        new QueryExecutor() {
            @Override
            public void doJob() throws Exception {
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, bno);
                stmt.executeUpdate();
            }
        }.executeAll();
    }


}
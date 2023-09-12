
-- 08월10일--

insert into T_reply(id, h_tier, descrim, bb_id, writer_id, title, content)
		values ('p001', 0, 'post', '000n', '0003', 'dog', 'i like');
	
insert into T_reply(id, h_tier, descrim, writer_id, content)
		values ('p001r000', 1, 'reply', '0003', 'neo');
insert into T_reply(id, h_tier, descrim, writer_id, content)
		values ('p001r000rr00', 2, 'reply', '0003', 'reply of reply');
insert into T_reply(id, h_tier, descrim, writer_id, content)
		values ('p001r000rr01', 2, 'reply', '0003', 'second reply of r')	;	
		
insert into T_reply(id, h_tier, descrim, writer_id, content)
		values ('p001r001', 1, 'reply', '0003', 'new name');
		
insert into T_reply(id, h_tier, descrim, bb_id, writer_id, title, content)
		values ('p002', 0, 'post', '000n', '0003', 'cat', 'i like')	;
insert into T_reply(id, h_tier, descrim, bb_id, title, content)
		values ('p003', 0, 'post', '000n', 'empty cat', 'bye bye')	;
		
		
		
		
		
		
		
		
		
insert into T_comp_hierarch(id, comp_hierarch, kind)
		values ('r000','p001r000','t2b');

		
				
INSERT INTO t_party(id, descrim, NAME, sex)
 VALUES ('0003','Member','홍길동',0);

	/**게시판의 모든 원글 목록 조회*/
	public List<PostVO> listAllPost(String boardId);	
		
select p.*, w.id w_id, w.descrim w_descrim, w.name w_name, w.sex w_sex, w.reg_dt w_reg_dt, w.upt_dt w_upt_dt
  from T_reply p left outer join T_party w
    on p.writer_id = w.id
 where p.bb_id = #{id};
 
 #{id}'000n'
 
  	select p.*, w.id w_id, w.descrim w_descrim, w.name w_name, w.sex w_sex, w.reg_dt w_reg_dt, w.upt_dt w_upt_dt
		  from T_reply p left outer join T_party w
		    on p.writer_id = w.id
		 where p.bb_id = '000n';
 
 	/**특정게시물에 따른 원글 상세(첨부파일 목록, 댓글 목록, 대댓글 목록이 채워짐)*/
	public PostVO findById(String id);
 	<!-- public PostVO findById(String id) { -->

		select r.*, w.*
		 from T_reply r left outer join T_party w
		   on r.writer_id = w.id
		where r.id like concat (#{id}, '%');
 
 #{id}'p001'
 
 		select r.*, w.*
		 from T_reply r left outer join T_party w
		   on r.writer_id = w.id
		where r.id like CONCAT ('p001', '%');

 
 
 
 
 
 
 	/**특정 게시판에 원글등록*/
	public int createPost(PostVO post);
 
 
 		insert into T_reply(id, h_tier, descrim, writer_id, content, bb_id, title)
		values (#{id}, 0, 'post', #{writer.id}, #{content}, #{boardVO.id}, #{title});
 
 insert into T_reply(id, h_tier, descrim, bb_id, writer_id, title, content)
		values (NEXT_PK('s_reply'), 0, 'post', '000n', '0003', 'dog', 'i like');
 
 
 
 
 
 
 	/**댓글 달기. parant의 id의 연결된 id 만들기*/
	public int createReply(@Param("parent") ReplyVO parent, @Param ("reply") ReplyVO reply); //댓글, 대댓글
 
insert into T_reply(id, h_tier, descrim, writer_id, content)
		values (concat(#{id},NEXT_PK('s_reply')), 0+1, 'reply', #{reply.writer.id}, #{reply.content});
 
 
insert into T_reply(id, h_tier, descrim, writer_id, content)
		values (concat('p001',NEXT_PK('s_reply')), 0+1, 'reply', '0003', 'neo');
 
 
 	/***/
	public int updatePost(PostVO post);
 
 	update T_reply
		   set title = #{title}
			   content = #{content}
		 where id = #{id};
		 
		 
	/***/
	public int updateReply(ReplyVO parent, ReplyVO reply);
	update T_reply
		   set content = #{content}
		 where id = #{id}	 ;
		 
		 
		 
		 
	/**id like로 지우기 */
	public int deleteReply(String id);	 
		 
	/**삭제 연습용*/
insert into T_reply(id, h_tier, descrim, bb_id, title, content)
		values ('p003', 0, 'post', '000n', 'empty cat', 'bye bye');			 
insert into T_reply(id, h_tier, descrim, writer_id, content)
		values (concat('p003',NEXT_PK('s_reply')), 0+1, 'reply', '0003', 'neo 0074')	;	 
insert into T_reply(id, h_tier, descrim, writer_id, content)
		values (concat('p0030002',NEXT_PK('s_reply')), 2, 'reply', '0003', 'neo 0041');	
	
	
	
	

		delete from T_reply
		 where id like concat (#{id}, '%');
		
		 
		 		delete from T_reply
		 where id like concat ('p003', '%');
		 
 
 
 -------------------------------------------------------------------
 
 
		   
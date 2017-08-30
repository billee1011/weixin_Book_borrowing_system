using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsBmsManage.DAL
{
    class BookDal
    {
        SqlHelper sh = new SqlHelper();
       public int AddSingleBook(string isbn,string num,string collectionnum)//添加单本书籍
        { int addnum = int.Parse(num);
            int cn = int.Parse(collectionnum);
            string sql = String.Format("update book set collectionnumber='{0}',counterpart='{1}' where ISBN='{2}'", (cn+ addnum).ToString(), (cn + addnum).ToString(), isbn);//更改book表
            List<string> ls = new List<string>();
            ls.Add(sql);
            for(int i = 0; i < addnum; i++)
            {
                string s = String.Format("insert into bookinfo (ISBN,ID,borrowed) values('{0}','{1}','{2}')", isbn, (cn + i).ToString(), "A");
                ls.Add(s);
            }
            string[] sqls = ls.ToArray();
            return sh.sqlTran1(sqls);


        }
        public object SelectBookNum(string info)//查询书籍馆藏量，以及书名
        {
            string sql = "select collectionnumber,bookname from book where ISBN=@arg1";
            object result=sh.getReader(sql,info);
            return result;
        }
        public object SelectBookAndBookInfo(string info)
        {
            string info1 = info + "%";
            string sql = "select book.ISBN,book.bookname,book.collectionnumber,bookinfo.ID,bookinfo.borrowed from book LEFT JOIN bookinfo on book.ISBN=bookinfo.ISBN where book.ISBN like @arg1 or book.bookname like @arg1";
            object result = sh.getReader(sql, info1);
            return result;

        }
        public int DeleteBookInfo(string isbn,string id,string collectionnum)
        {
            int num = int.Parse(collectionnum);
            string sql = "delete from bookinfo where ISBN=@arg3 and ID=@arg4";
            string sql1 = "update book set collectionnumber=@arg5 where ISBN=@arg6";
            string[] sqls = new string[]{ sql,sql1};
            return sh.sqlTran(sqls,2,2,isbn,id,(num-1).ToString(),isbn);
        }
        public object SelectBookState(string isbn,string id)
        {
            
            string sql = "select bookinfo.ISBN,bookinfo.id,bookinfo.Userphone,bookinfo.borrowed,bookinfo.date,book.bookname from book,bookinfo where bookinfo.ISBN=@arg1 and bookinfo.ID=@arg2 and bookinfo.ISBN=book.ISBN";
            object result = sh.getReader(sql,isbn,id);
            return result;

        }
        public object SelectBookState(string bookname)
        {
            string sql = "select bookinfo.ISBN,bookinfo.id,bookinfo.Userphone,bookinfo.borrowed,bookinfo.date,book.bookname from book,bookinfo where (book.bookname=@arg1 or book.ISBN=@arg1) and bookinfo.ISBN=book.ISBN";
            object result = sh.getReader(sql,bookname);
            return result;
        }
        public object SelectBookISBN(string info)//查询ISBN
        {
            string sql = "select count(*) from book where ISBN=@arg1";
            object result = sh.getSingleValue(sql, info);
            return result;
        }
        public int AddNewBook(params object[] args)//增加书籍
        { int num = int.Parse(args[8].ToString());
            string sql = "insert into book (bookname,simpintroduce,printingtime,booksize,ISBN,category,pcategory,img,collectionnumber,counterpart,Pinyin,initial,callnumber,author,publisher,collectionplace)" +
                "values(@arg1,@arg2,@arg3,@arg4,@arg5,@arg6,@arg7,@arg8,@arg9,@arg9,@arg10,@arg11,@arg12,@arg13,@arg14,@arg15)";
            List<string> ls = new List<string>();
            ls.Add(sql);
            for (int i = 0; i <num; i++)
            {
                string s = String.Format("insert into bookinfo (ISBN,ID,borrowed) values('{0}','{1}','{2}')", args[4], i.ToString(), "A");
                ls.Add(s);
            }
            string[] sqls = ls.ToArray();
            return sh.sqlTran2(sqls, args);
        }
        public object SelectBookForDelete(string info)
        {
            string sql="select bookname, book.ISBN,collectionnumber,count(bookinfo.borrowed) from book LEFT JOIN bookinfo on book.isbn = bookinfo.isbn and bookinfo.borrowed = 'A' where book.ISBN = @arg1 or book.bookname=@arg1";
            return sh.getReader(sql,info);
        }
        public int DeleteAllBook(string info)
        {
            string sql = "delete from book where ISBN=@arg3";
            string sql2 = "delete from bookinfo where ISBN=@arg4";
            string[] sqls = new string[] { sql, sql2 };
            return sh.sqlTran(sqls, 1, 1, info, info);

        }
        public object SelectForUpdate(string info)
        {
            string info1 = info + "%";
            string sql = "select bookname,isbn,author,pcategory,category,publisher,printingtime,img,booksize,Pinyin,callnumber,initial,collectionplace,simpintroduce from book where isbn like @arg1 or bookname like @arg1";
            return sh.selectAll(sql,"bookinfo",info1);
        }
         public object SelectAllBookInfo(string info)
        {
            string info1 = info + "%";
            string sql = "select * from book where isbn like @arg1 or bookname like @arg1";
            return sh.selectAll(sql, "bookAllInfo", info1);
        }
        public int UpdateBook(params object[] args)
        {
            string sql = "update book set bookname=@arg1,author=@arg2,pcategory=@arg3,category=@arg4,publisher=@arg5,printingtime=@arg6,img=@arg7," +
                "booksize=@arg8,Pinyin=@arg9,callnumber=@arg10,initial=@arg11,collectionplace=@arg12,simpintroduce=@arg13 where isbn=@arg14";
            return sh.nonQuery(sql, args);
        }

      
    }
}

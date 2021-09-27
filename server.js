const express = require('express');
const app = express();
const bodyParser = require('body-parser');
app.use(express.urlencoded({ extended: true }));
const MongClient = require('mongodb').MongoClient;
const methodOverride = require('method-override');
app.use(methodOverride('_method'));

const http = require('http').createServer(app);
const { Server } = require("socket.io");
const io = new Server(http);


app.set('view engine', 'ejs');
app.use('/public', express.static('public'))
require('dotenv').config();


var db;
MongClient.connect(process.env.DB_URL, function (에러, client) {
    //연결되면 할일 
    if (에러) {
        return console.log(에러);
    }
    db = client.db('todoapp');
    http.listen(process.env.PORT, function () {
        console.log("Listening on" + process.env.PORT);

    });


});
//  const bodyParser = require('body-parser');
//  app.use(bodyParser.urlencoded({extended : true}));





app.get('/detail/:id', function (요청, 응답) {
    db.collection('post').findOne({ _id: parseInt(요청.params.id) }, function (에러, 결과) {
        //console.log(결과);
        console.log(요청.params);
        //if (에러) { return console.log(에러) };
        응답.render('detail.ejs', { data: 결과 });


    });
})

app.get('/edit/:id', function (요청, 응답) {

    db.collection('post').findOne({ _id: parseInt(요청.params.id) }, function (에러, 결과) {
        // console.log(에러);
        //console.log(결과);
        console.log(요청.params);
        // if (에러) { return console.log(에러) };
        응답.render('edit.ejs', { post: 결과 });

    });

});

app.put('/edit', function (요청, 응답) {
    //폼에 담긴 제목데이터, 날짜 데이터를 가지고 
    //db.collection 에다가 업데이트함

    db.collection('post').updateOne({ _id: parseInt(요청.body.id) }, { $set: { 제목: 요청.body.tit, 날짜: 요청.body.date } }, function (에러, 결과) {
        console.log('수정완료');
        응답.redirect('/list');
    })
});

app.get('/search', function (요청, 응답) {
    //console.log(요청.query.value);

    var 검색조건 = [
        {
            $search: {
                index: 'titleSearch',
                text: {
                    query: 요청.query.value,
                    path: ['제목', '날짜']  // 제목날짜 둘다 찾고 싶으면 ['제목', '날짜']
                }
            }
        },
        { $sort: { _id: 1 } },
        { $project: { 제목: 1, _id: 1, 날짜: 1, score: { $meta: "searchScore" } } }
    ]
    db.collection('post').aggregate(검색조건).toArray(function (에러, 결과) {
        console.log(결과);
        응답.render('search.ejs', { posts: 결과 });


    });
    //응답.render('search.ejs', 응답);
});

const passport = require('passport');
const LocalStrategy = require('passport-local').Strategy;
const session = require('express-session');
const { application } = require('express');

app.use(session({ secret: '5758', resave: true, saveUninitialized: false }));
app.use(passport.initialize());
app.use(passport.session());

//미들웨어 웹서버는 요청 - 응답해주는 머신
// 미들웨어 : 요청 - 응답 중간에 뭔가 실행되는 코드 


app.get('/login', function (요청, 응답) {
    응답.render('login.ejs');
});

app.post('/login', passport.authenticate('local', {
    failureRedirect: '/fail'
}), function (요청, 응답) {
    응답.redirect('/go');
});
app.get('/fail', function (req, 응답) {
    응답.status(400).send({ message: '잘못된 로그인입니다.' });
    //응답.redirect('/login')
})






app.get('/mypage', 로그인했나요, function (요청, 응답) {
    console.log(요청.user);
    응답.render('mypage.ejs', { 사용자: 요청.user });
});

function 로그인했나요(요청, 응답, next) {
    if (요청.user) {
        next()
    } else {
        //alert('로그인 해주세요.');
        응답.redirect('/login');
    }
}





passport.use(new LocalStrategy({
    usernameField: 'id',
    passwordField: 'pw',
    session: true,
    passReqToCallback: false,
}, function (입력한아이디, 입력한비번, done) {
    //console.log(입력한아이디, 입력한비번);
    db.collection('login').findOne({ id: 입력한아이디 }, function (에러, 결과) {
        if (에러) return done(에러);

        if (!결과) return done(null, false, { message: '존재하지않는 아이디요' })
        if (입력한비번 == 결과.pw) {
            return done(null, 결과);
        } else {
            return done(null, false, { message: '비번틀렸어요' });
        }
    });
}));

//로그인 성공 -> 세션정보를 만듦 -> 마이페이지 방문시 세션검사

passport.serializeUser(function (user, done) {
    done(null, user.id);
});


passport.deserializeUser(function (아이디, done) {
    db.collection('login').findOne({ id: 아이디 }, function (에러, 결과) {
        done(null, 결과)
    })
});

app.post('/add', function (req, 응답) {
    //응답.send('전송완료');
    console.log(`Title : ${req.body.tit}`);
    console.log(`DAte : ${req.body.date}`);
    db.collection('counter').findOne({ name: '게시물갯수' }, function (에러, 결과) {
        console.log(결과.totalPost);
        var totalCount = 결과.totalPost;

        var saveData = { _id: totalCount + 1, 작성자: req.user._id, 제목: `${req.body.tit}`, 날짜: `${req.body.date}` }
        db.collection('post').insertOne(saveData, function (에러, 결과) {

            console.log('저장완료');
            db.collection('counter').updateOne({ name: '게시물갯수' }, { $inc: { totalPost: 1 } }, function (에러, 결과) {
                if (에러) { return console.log(에러) };
                응답.redirect('/list');
            });
            //set : {totalPost : 바꿀값}
            //inc : {totalPost : 증가할 값}
        });


    });

});

app.get('/go', 로그인했나요, function (요청, 응답) {
    //console.log(요청);
    응답.render('index.ejs');

});
app.get('/write', 로그인했나요, function (요청, 응답) {
    // console.log(요청);
    응답.render('write.ejs', 응답);
});

app.get('/list', 로그인했나요, function (요청, 응답) {

    db.collection('post').find().toArray(function (에러, 결과) {
        console.log(결과);
        console.log(요청.user._id);
        응답.render('list.ejs', { posts: 결과, user: 요청.user._id });


    }); // 데이터를 다 찾아주세요.

    //디비에 저장된 post라는 collection 안의 모든 데이트를 꺼내주세요.

});

app.delete('/delete', function (요청, 응답) {
    console.log(요청.body);
    요청.body._id = parseInt(요청.body._id);

    var 삭제할데이터 = { _id: 요청.body._id, 작성자: 요청.user._id }
    db.collection('post').deleteOne(삭제할데이터, function (에러, 결과) {
        console.log('삭제완료');
        if (결과) { console.log(결과) };
        응답.status(200).send({ message: '성공했습니다.' });
        //응답.status(400).send({ message : '실패했습니다.'});
    })
    //응답.send('삭제완료');
})


app.post('/join', function (req, 응답) {
    //응답.send('전송완료');
    console.log(`ID : ${req.body.id}`);
    console.log(`PW : ${req.body.pw}`);
    db.collection('login').findOne({ id: req.body.id }, function (에러, 결과) {
        if (결과) {
            console.log('사용중인 아이디 입니다. ');
            //alert('사용중인 아이딥니다.');
            응답.status(400).send({ message: '사용중인 아이디 입니다.' });
        } else {



            db.collection('login').insertOne({ id: `${req.body.id}`, pw: `${req.body.pw}` }, function (에러, 결과) {

                console.log('등록완료');
                // if (에러) { return console.log(에러) };
                응답.redirect('/go');

                //set : {totalPost : 바꿀값}
                //inc : {totalPost : 증가할 값}
            });

        }
    });

});

app.use('/board/sub', require('./routes/board.js'));

let multer = require('multer');
var storage = multer.diskStorage({
    destination: function (req, file, cb) {
        cb(null, './public/image')
    },
    filename: function (req, file, cb) {
        cb(null, file.originalname + new Date())
    },
    filefilter: function (req, file, cb) {
        var ext = path.extname(file.originalname);
        if (ext !== '.png' && ext !== '.jpg' && ext !== '.jpeg') {
            return callback(new Error('PNG, JPG만 업로드하세요'))
        }
        cb(null, true)
    },
    limits: {
        fileSize: 1024 * 1024
    }
});

var upload = multer({ storage: storage });



app.get('/upload', function (요청, 응답) {
    응답.render('upload.ejs');
})
app.post('/upload', upload.single('profile'), function (요청, 응답) {
    응답.send('업로드 완료');
});

app.get('/image/:imagename', function (요청, 응답) {
    응답.sendfile(__dirname + '/public/image/' + 요청.params.imagename);
})


app.get('/chat', 로그인했나요, function (요청, 응답) {
    응답.render('chat.ejs',{id:요청.user._id})
});

// io.on('connection', function (socket) {
//     console.log('연결되었어요');

    

//     socket.on('인삿말', function (data) {
//         console.log(data)
      
//         io.emit('전달',data);
       
//     });

// });

var chat1= io.of('/채팅방1');
chat1.on('connection', function (socket) {
    let roomNo="room0";
    let userid;
    console.log('채팅방1에 연결되었어요');
    
    socket.on('joinRoom',function(data){
        if(data.room !=roomNo){
        socket.join(data.room);    // 클라이언트를 msg에 적힌 room으로 참여 시킴
        roomNo=data.room;
        userid=data.id;
        chat1.to(roomNo).emit('입장',data.id+" 입장");
        }
    })
    socket.on('인삿말', function (data) {
        console.log(data)
        chat1.to(roomNo).emit('누구',userid);
        chat1.to(roomNo).emit('전달',data.message);
        //io.to(data.roomName).emit('broadcast', data.msg); 
    });

});
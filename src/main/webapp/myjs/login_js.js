new Vue({    el: '#login',

    data: {
        user:{},

    },
    methods: {
        getLogin: function () {
            var _this = this;
            axios.post('/us/goLogin.do',_this.user).then(function (response) {
                if (response.data.status==0){
                    alert(response.data.msg)
                    _this.getUserInSession();
                    location.href="pages/main.html";
                }else{
                    alert(response.data.msg)
                }
            })
        },
        getUserInSession:function () {
            var _this = this
            this.user = {}
            axios.get('/us/getUserInSession.do').then(function (response) {
                _this.user = response.data  //获取用户登录后在首页的展示信息
            })
        }
    }
})
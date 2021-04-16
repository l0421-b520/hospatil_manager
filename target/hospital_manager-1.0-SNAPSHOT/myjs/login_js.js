new Vue({
    el: '#login',
    data: {
        user:{}
    },
    methods: {
        getLogin: function () {
            var _this = this;
            axios.post('/us/goLogin.do',_this.user).then(function (response) {
                if (response.data.status==0){
                    alert(response.data.msg)
                    location.href="pages/main.html";
                }else{
                    alert(response.data.msg)
                }
            })
        }
    }
})
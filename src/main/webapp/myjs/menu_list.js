var vm = new Vue({
    el: '#meundiv',
    data:{
        menuList:[],
        pid:1,
        entity:{isbutton:0},
        page:{},
        pageNum:1,
        pageSize:5
    },
    methods: {
        findAll: function () {
            var _this =this
            axios.post('../mu/getMenus.do?pid='+this.pid+'&pageNum='+this.pageNum+'&pageSize='+this.pageSize).then(function (response) {
                _this.menuList = response.data.list
                _this.page = response.data
            })
        },
        queryLast:function (pid) {
            var _this =this
            this.pid = pid;
            axios.post('../mu/getMenus.do?pid='+_this.pid+'&pageNum='+this.pageNum+'&pageSize='+this.pageSize).then(function (response) {
                _this.menuList = response.data.list
                _this.page = response.data
            })
        },
        addMsg: function () {
            this.entity={};
            document.getElementById("tb2").style.display="block";
        },
        saveMsg: function () {
            var _this = this
            this.entity.pid = this.pid
            axios.post('/mu/saveSelect.do',_this.entity).then(function (response) {
                if (response.data=="yes"){
                    //response.data.status==0
                    //alert(response.data.msg)
                    alert("成功！")
                    location.reload(true)
                }else{
                    //alert(response.data.msg)
                    alert("失败！")
                }

            })
        },
        deleteByid: function (id) {
            var _this= this
            axios.post('/mu/deleteByid.do?pid='+id).then(function (response) {
                if (response.data=="yes"){
                    _this.queryLast(_this.pid)
                }
            })
        },
        packing: function (pageNum) {
            this.pageNum = pageNum;
            this.queryLast(this.pid)
        }
    }
})
vm.findAll();


$(function () {
    var left='<ul class="layui-nav layui-nav-tree"  lay-filter="test">\n' +
        '                <li class="layui-nav-item layui-nav-itemed">\n' +
        '                    <a class="" href="../manageWB/queryPage">微众银行</a>\n' +
        '                    <dl class="layui-nav-child">\n' +
        '                        <dd><a href="../manageWB/queryWelthPage">财富发放记录</a></dd>\n' +
        '                        <dd><a href="../manageWB/exportWBPage">数据导出</a></dd>\n' +
        '                        <dd><a href="../manageWB/importWBPage">数据导入</a></dd>\n' +
        '                        <dd><a href="../manageWB/fiveimportWBPage">五日前数据发放财富</a></dd>\n' +
        '                        <dd><a href="../manageWB/webankopenpage">微众开关</a></dd>\n' +
        '                    </dl>\n' +
        '                </li>\n' +
        '                <li class="layui-nav-item">\n' +
        '                    <a href="javascript:;">报价</a>\n' +
        '                    <dl class="layui-nav-child">\n' +
        '                        <dd><a href="../manageWB/queryofferPage">报价信息</a></dd>\n' +
        '                        <dd><a href="../manageWB/queryhotcityPage">热门城市</a></dd>\n' +
        '                        <dd><a href="../manageWB/offerisopenPage">报价开关</a></dd>\n' +
        '                    </dl>\n' +
        '                </li>\n' +
        '                <li class="layui-nav-item">\n' +
        '                    <a href="javascript:;">花呗交易</a>\n' +
        '                    <dl class="layui-nav-child">\n' +
        '                        <dd><a href="../manageWB/zanClickLogPage">交易记录</a></dd>\n' +
        '                        <dd><a href="../manageWB/zanClickfqPage">分期费率</a></dd>\n' +
        '                        <dd><a href="../manageWB/refundPage">申请退款</a></dd>\n' +
        '                        <dd><a href="../manageWB/alreadyRefundPage">退款记录</a></dd>\n' +
        '                        <dd><a href="../manageWB/stroreOpenOrderPage">商家开户</a></dd>\n' +
        '                        <dd><a href="../manageWB/statisticsPage">统计数据</a></dd>\n' +
        '                        <dd><a href="../manageWB/zanopenPage">花呗开关</a></dd>\n' +
        '                    </dl>\n' +
        '                </li>\n' +
        /*'                <li class="layui-nav-item"><a href="javascript:;">云市场</a></li>\n' +
        '                <li class="layui-nav-item"><a href="">发布商品</a></li>\n' +*/
        '            </ul>';
    $('.layui-side-scroll').html(left);
    var  header=' <div class="layui-logo">layui 后台布局</div>\n' +
        '        <!-- 头部区域（可配合layui已有的水平导航） -->\n' +
        '        <ul class="layui-nav layui-layout-left">\n' +
        '            <li class="layui-nav-item"><a href="">控制台</a></li>\n' +
        '            <li class="layui-nav-item"><a href="">商品管理</a></li>\n' +
        '            <li class="layui-nav-item"><a href="">用户</a></li>\n' +
        '            <li class="layui-nav-item">\n' +
        '                <a href="javascript:;">其它系统</a>\n' +
        '                <dl class="layui-nav-child">\n' +
        '                    <dd><a href="">邮件管理</a></dd>\n' +
        '                    <dd><a href="">消息管理</a></dd>\n' +
        '                    <dd><a href="">授权管理</a></dd>\n' +
        '                </dl>\n' +
        '            </li>\n' +
        '        </ul>\n' +
        '        <ul class="layui-nav layui-layout-right">\n' +
        '            <li class="layui-nav-item">\n' +
        '                <a href="javascript:;">\n' +
        '                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">\n' +
        '                    😝\n' +
        '                </a>\n' +
        '                <dl class="layui-nav-child">\n' +
        '                    <dd><a href="">基本资料</a></dd>\n' +
        '                    <dd><a href="">安全设置</a></dd>\n' +
        '                </dl>\n' +
        '            </li>\n' +
        '            <li class="layui-nav-item"><a href="">退了</a></li>\n' +
        '        </ul>';
    $('.layui-header').html(header)
});

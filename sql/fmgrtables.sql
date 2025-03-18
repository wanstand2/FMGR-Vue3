
drop table if exists fmgre_material;
create table fmgre_material (
  materail_id       bigint(20)      not null auto_increment    comment '原料id',
  materail_type_dictid  varchar(8)  default '0'                  comment '原料类别（字典)',
  materail_code     varchar(20)     default ''                 comment '原料编码',
  materail_name     varchar(20)     default ''                 comment '原料名称',
  unit_dictid       varchar(8)      default '0'                comment '基本单位（字典)',
  valuation_amount  decimal(18,2)   default 0                  comment '计价量',
  price_sale        decimal(18,2)   default 0                  comment '销售价格',
  storage_dictid    bigint(20)      default 0                  comment '存储条件（字典)',
  comment           varchar(50)     default ''                 comment '备注',
  primary key (materail_id)
) engine=innodb auto_increment=100 comment = '原材料表';

drop table if exists fmgre_supplier;
create table fmgre_supplier (
  supplier_id       bigint(20)      not null auto_increment    comment '供应商id',
  supplier_name     varchar(20)     default ''                 comment '供应商名称',
  supplier_code     varchar(20)     default ''                 comment '供应商编码',
  supplier_detail   varchar(50)     default ''                 comment '供应商描述',
  address           varchar(50)     default ''                 comment '供应商地址',
  contact_name      varchar(20)     default ''                 comment '联系人姓名',
  contact_phone     varchar(20)     default ''                 comment '联系人电话',
  primary key (supplier_id)
) engine=innodb auto_increment=100 comment = '供应商表';

drop table if exists fmgre_franchise_store;
create table fmgre_franchise_store (
  dept_id           bigint(20)      not null                   comment '门店对应部门id',
  store_address     varchar(50)     default ''                 comment '门店地址',
  store_detail      varchar(50)     default ''                 comment '门店描述',
  primary key (dept_id)
) engine=innodb comment = '门店表';

drop table if exists fmgre_supplier_brand;
create table fmgre_supplier_brand (
  brand_id          bigint(20)      not null auto_increment    comment '供应品牌id',
  brand_name        varchar(20)     default ''                 comment '供应品牌名称',
  brand_detail      varchar(50)     default ''                 comment '供应品牌描述',
  primary key (brand_id)
) engine=innodb auto_increment=100 comment = '供应商品牌表';

drop table if exists fmgre_supplier_quote;
create table fmgre_supplier_quote (
  quote_id          bigint(20)      not null auto_increment    comment '报价id',
  materail_id       bigint(20)      default 0                  comment '原料id',
  supplier_id       bigint(20)      default 0                  comment '供应商id',
  supplier_brand_id bigint(20)      default 0                  comment '供应品牌id(0为散装)',
  pack_unit_dictid  varchar(8)      default 0                  comment '包装单位（字典)',
  pack_num          decimal(18,2)   default 0                  comment '包装数量(子包装数)',
  pack_size         decimal(18,2)   default 0                  comment '包装大小(包装总和)',
  sub_pack_unit_dictid  varchar(8)  default '0'                comment '子包装单位（字典)',
  sub_pack_num      bigint(20)      default 0                  comment '子包装数量(子包装个数)',
  sub_pack_size     decimal(18,2)   default 0                  comment '子包装大小(子包装总和)',
  quota_time        datetime                                   comment '报价时间',
  quota_price       decimal(18,2)   default 0                  comment '报价价格',
  quota_comment     varchar(50)     default ''                 comment '报价说明',
  primary key (quote_id)
) engine=innodb auto_increment=100 comment = '供应商报价表';

drop table if exists fmgre_purchase_requir;
create table fmgre_purchase_requir (
  requir_id         bigint(20)      not null auto_increment   comment '采购需求id',
  dept_id           bigint(20)      not null default 0        comment '送达门店id',
  requir_user_id    bigint(20)      default 0         comment '需求者id',
  requir_time       datetime                          comment '需求时间',
  requir_comment    varchar(50)     default ''        comment '需求说明',
  primary key (requir_id)
) engine=innodb auto_increment=100 comment = '采购需求表';

drop table if exists fmgre_purchase_order;
create table fmgre_purchase_order (
  order_id          bigint(20)      not null auto_increment    comment '采购订单id',
  user_id           bigint(20)      default 0                  comment '采购者id',
  supplier_id       bigint(20)      default 0                  comment '供应商id',
  order_code        varchar(20)     default ''                 comment '订单编码',
  delivery_dictid   varchar(8)         default '0'             comment '交付方式（字典)',
  order_time        datetime                                   comment '采购时间',
  order_total_price        decimal(18,2)    default 0          comment '采购总价',
  order_comment     varchar(50)      default ''                comment '采购说明',
  payment_id        bigint(20)       default 0                 comment '付款id(0未支付)',
  primary key (order_id)
) engine=innodb auto_increment=100 comment = '采购订单表';

drop table if exists fmgre_purchase_record;
create table fmgre_purchase_item (
  item_id           bigint(20)      not null auto_increment    comment '采购记录id',
  requir_id         bigint(20)      default 0                  comment '采购需求id',
  order_id          bigint(20)      default 0                  comment '采购订单id',
  materail_id       bigint(20)      default 0                  comment '原料id',
  requir_num        decimal(18,2)   default 0                  comment '希望采购数',
  requir_unit_dictid        varchar(8)       default '0'       comment '希望采购基本单位（字典)',
  order_num         decimal(18,2)   default 0                  comment '实际采购数',
  order_amount      decimal(18,2)   default 0                  comment '实际采购量',
  quota_id          bigint(20)      default 0                  comment '采购适用报价id',
  item_total_price          decimal(18,2)    default 0         comment '单项采购总价',
  primary key (item_id)
) engine=innodb auto_increment=100 comment = '采购记录表';

drop table if exists fmgre_finance_bank;
create table fmgre_finance_bank (
  bank_id           bigint(20)      not null auto_increment    comment '银行id',
  bank_name         varchar(40)     default ''                 comment '银行名称',
  bank_desc         varchar(80)     default ''                 comment '银行描述',
  swift_code        varchar(20)     default ''                 comment 'SWIFT代码',
  primary key (back_id)
) engine=innodb auto_increment=100 comment = '银行表';

drop table if exists fmgre_finance_account;
create table fmgre_finance_account (
  account_id        bigint(20)      not null auto_increment    comment '账户id',
  user_id           bigint(20)      default 0                  comment '账户拥有者id(私人)',
  dept_id           bigint(20)      default 0                  comment '账户拥有者id(部门)',
  account_dictid    varchar(8)      default '0'                comment '账户类型（字典)',
  account_name      varchar(40)     default ''                 comment '账户名称',
  account_number    varchar(40)     default ''                 comment '账号',
  bank_id           bigint(20)      default 0                  comment '开户行id',
  bank_info         varchar(80)     default ''                 comment '开户行信息',
  primary key (account_id)
) engine=innodb auto_increment=100 comment = '银行账户表';

drop table if exists fmgre_finance_account_balance;
create table fmgre_finance_account_balance (
  account_id        bigint(20)      not null                   comment '账户id',
  account_balance   decimal(18,2)   default 0                  comment '账户余额',
  primary key (account_id)
) engine=innodb comment = '银行账户余额表';

drop table if exists fmgre_finance_payment;
create table fmgre_finance_payment (
  payment_id        bigint(20)      not null auto_increment    comment '支付id',
  out_acc_id        bigint(20)      default 0                  comment '付款账户id',
  in_acc_id         bigint(20)      default 0                  comment '收款账户id',
  user_id           bigint(20)      default 0                  comment '操作用户id',
  order_id          bigint(20)      default 0                  comment '订单id(若有)',
  payment_comment   varchar(80)     default ''                 comment '支付说明',
  payment_amount    decimal(18,2)   default 0                  comment '支付金额',
  payment_time      datetime                                   comment '支付时间',
  primary key (payment_id)
) engine=innodb auto_increment=100 comment = '付款流水表';

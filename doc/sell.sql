create table `product_info` (
	`product_id` varchar(32) not null,
	`product_name` varchar(64) not null comment '��Ʒ����',
	`product_price` decimal(8,2) not null comment '����',
	`product_stock` int not null comment '���',
	`product_description` varchar(64) comment '����',
	`product_icon` varchar(512) comment 'Сͼ',
	`product_status` tinyint(3) not null comment '商品状态',
	`category_type` int not null comment '��Ŀ���',
	`create_time` timestamp not null default current_timestamp comment '����ʱ��',
	`update_time` timestamp not null default current_timestamp on update current_timestamp comment '�޸�ʱ��',
	primary key (`product_id`)
) comment '��Ʒ��';

create table `product_category` (
	`category_id` int not null auto_increment,
	`category_name` varchar(64) not null comment '��Ŀ����',
	`category_type` int not null comment '��Ŀ���',
	`create_time` timestamp not null default current_timestamp comment '����ʱ��',
	`update_time` timestamp not null default current_timestamp on update current_timestamp comment '�޸�ʱ��',
	primary key (`category_id`),
	unique key `uqe_category_type` (`category_type`)
) comment '��Ŀ��';

create table `order_master` (
	`order_id` varchar(32) not null,
	`buyer_name` varchar(32) not null comment '�������',
	`buyer_phone` varchar(32) not null comment '��ҵ绰',
	`buyer_address` varchar(32) not null comment '��ҵ�ַ',
	`buyer_openid` varchar(64) not null comment '���΢��openid',
	`order_amount` decimal(8,2) not null comment '�����ܽ��',
	`order_status` tinyint(3) not null default '0' comment '����״̬,Ĭ��0���µ�',
	`pay_status` tinyint(3) not null default '0' comment '֧��״̬,Ĭ��0δ֧��',
	`create_time` timestamp not null default current_timestamp comment '����ʱ��',
	`update_time` timestamp not null default current_timestamp on update current_timestamp comment '�޸�ʱ��',
	primary key (`order_id`),
	key `idx_buyer_openid` (`buyer_openid`)
) comment '������';

create table `order_detail` (
	`detail_id` varchar(32) not null,
	`order_id` varchar(32) not null,
	`product_id` varchar(32) not null,
	`product_name` varchar(64) not null comment '��Ʒ����',
	`product_price` decimal(8,2) not null comment '��Ʒ�۸�',
	`product_quantity` int not null comment '��Ʒ����',
	`product_icon` varchar(512) comment '��ƷСͼ',
	`create_time` timestamp not null default current_timestamp comment '����ʱ��',
	`update_time` timestamp not null default current_timestamp on update current_timestamp comment '�޸�ʱ��',
	primary key (`detail_id`),
	key `idx_order_id` (`order_id`)
) comment '���������';

create table `seller_info` (
	`seller_id` varchar(32) not null,
	`username` varchar(32) not null,
	`password` varchar(32) not null,
	`openid` varchar(64) not null comment '微信openid',
	`create_time` timestamp not null default current_timestamp comment '����ʱ��',
	`update_time` timestamp not null default current_timestamp on update current_timestamp comment '�޸�ʱ��',
	primary key (`seller_id`)
) comment '卖家信息表';

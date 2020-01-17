select * from dir_file;

insert into dir_file(directory_id, file_name, is_directory, size)
values(1, 'distr', true, null);

insert into dir_file(directory_id, file_name, is_directory, size)
values(2, 'Presentation.ppt', false, 3800);

select * from directory;

insert into directory values(1, 'd:\\TSystems\\', SYSDATE());
insert into directory(path, added) values('d:\\Avalon\\', SYSDATE());
insert into directory(path, added) values('d:\\git\\', SYSDATE());

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` INTEGER NOT NULL auto_increment,
  `username` VARCHAR(255),
  `gender` VARCHAR(255),
  `age` VARCHAR(255),
  `email` VARCHAR(255),
  `password` VARCHAR(255) NOT NULL,
  `nickname` VARCHAR(255),
  `phone` VARCHAR(255) NOT NULL,
  `pic_url` VARCHAR(255),
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `song` (
 `song_id` INTEGER NOT NULL auto_increment,
 `album_id` INTEGER,
 `artist_id` INTEGER,
 `name` VARCHAR(255) NOT NULL,
 `album` VARCHAR(255),
 `artist` VARCHAR(255),
 `pop` INTEGER,
 `mark` INTEGER,
 `publish_time` VARCHAR(255),
 `url` VARCHAR(255) NOT NULL,
 `lyric_url` VARCHAR(255),
 `pic_url` VARCHAR(255),
 `duration` INTEGER,
 `type` VARCHAR(255),
 PRIMARY KEY (`song_id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `artist` (
 `artist_id` INTEGER NOT NULL auto_increment,
 `name` VARCHAR(255),
 `profile` VARCHAR(255),
 `location` INTEGER,
 `pic_url` VARCHAR(255) ,
 `song_size` INTEGER ,
 `album_size` INTEGER ,
 `gender` INTEGER,
 `first_letter` VARCHAR(255),
 PRIMARY KEY (`artist_id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `likes` (
 `like_id` INTEGER NOT NULL auto_increment,
 `user_id` INTEGER ,
 `song_id` INTEGER ,
 `album_id` INTEGER ,
 `artist_id` INTEGER ,
 `playlist_id` INTEGER ,
 `type` INTEGER NOT NULL,
 PRIMARY KEY (`like_id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `log` (
 `log_id` INTEGER NOT NULL auto_increment,
 `user_id` INTEGER,
 `song_id` INTEGER,
 `username` VARCHAR(255) COMMENT '用户名',
 `songname` VARCHAR(255) COMMENT '歌曲名',
 `time` VARCHAR(255) COMMENT '日志时间',
 `type` INTEGER NOT NULL COMMENT '1为注册2为播放3为下载',
 PRIMARY KEY (`log_id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `admin_user` (
 `admin_id` INTEGER NOT NULL auto_increment,
 `adminname` VARCHAR(255) NOT NULL,
 `password` VARCHAR(255) NOT NULL,
 PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `album` (
 `album_id` INTEGER NOT NULL auto_increment,
 `size` INTEGER,
 `name` VARCHAR(255),
 `type` VARCHAR(255) COMMENT '比如流行、古典',
 `artist` VARCHAR(255),
 `artist_id` INTEGER,
 `profile` VARCHAR(255),
 `publish_time` VARCHAR(255) COMMENT '和歌曲发行时间相同',
 `pic_url` VARCHAR(255),
 PRIMARY KEY (`album_id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `playlist` (
 `playlist_id` INTEGER NOT NULL auto_increment,
 `user_id` INTEGER,
 `mark` INTEGER,
 `size` INTEGER,
 `create_time` VARCHAR(255),
 `user` VARCHAR(255),
 `playlist` VARCHAR(255),
 `profile` VARCHAR(255),
 `tag` VARCHAR(255),
 `pic_url` VARCHAR(255),
 PRIMARY KEY (`playlist_id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `mv` (
 `movie_id` INTEGER NOT NULL auto_increment,
 `url` VARCHAR(255),
 `pic_url` VARCHAR(255),
 `duration` INTEGER COMMENT '时长',
 `movie` VARCHAR(255) NOT NULL COMMENT '名字',
 `artist` VARCHAR(255) COMMENT '表演者',
 PRIMARY KEY (`movie_id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `playlist_songs` (
 `id` INTEGER NOT NULL auto_increment,
 `song_id` INTEGER NOT NULL,
 `playlist_id` INTEGER NOT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB;


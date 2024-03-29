USE [master]
GO
/****** Object:  Database [School_Management]    Script Date: 28/06/2022 6:48:56 CH ******/
CREATE DATABASE [School_Management]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'School_Management', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.HUNGTRUONG\MSSQL\DATA\School_Management.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'School_Management_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.HUNGTRUONG\MSSQL\DATA\School_Management_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [School_Management] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [School_Management].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [School_Management] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [School_Management] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [School_Management] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [School_Management] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [School_Management] SET ARITHABORT OFF 
GO
ALTER DATABASE [School_Management] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [School_Management] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [School_Management] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [School_Management] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [School_Management] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [School_Management] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [School_Management] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [School_Management] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [School_Management] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [School_Management] SET  ENABLE_BROKER 
GO
ALTER DATABASE [School_Management] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [School_Management] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [School_Management] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [School_Management] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [School_Management] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [School_Management] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [School_Management] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [School_Management] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [School_Management] SET  MULTI_USER 
GO
ALTER DATABASE [School_Management] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [School_Management] SET DB_CHAINING OFF 
GO
ALTER DATABASE [School_Management] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [School_Management] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [School_Management] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [School_Management] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [School_Management] SET QUERY_STORE = OFF
GO
USE [School_Management]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 28/06/2022 6:48:56 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[email] [varchar](50) NOT NULL,
	[password] [varchar](30) NULL,
	[fullname] [varchar](50) NULL,
	[role] [varchar](10) NULL,
	[status] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Attendance]    Script Date: 28/06/2022 6:48:56 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Attendance](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[slotId] [int] NULL,
	[studentInCourseId] [int] NULL,
	[date] [date] NULL,
	[status] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 28/06/2022 6:48:56 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[subjectId] [int] NULL,
	[major] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Center]    Script Date: 28/06/2022 6:48:56 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Center](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[courseId] [int] NULL,
	[name] [nvarchar](50) NULL,
	[address] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Course]    Script Date: 28/06/2022 6:48:56 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Course](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[subjectId] [int] NULL,
	[lecturerId] [int] NULL,
	[centerId] [int] NULL,
	[studentId] [int] NULL,
	[joinDate] [date] NULL,
	[endDate] [date] NULL,
	[lecturerName] [nvarchar](50) NULL,
	[centerName] [nvarchar](50) NULL,
	[slot] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Lecturer]    Script Date: 28/06/2022 6:48:56 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Lecturer](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[fullname] [nvarchar](50) NULL,
	[avatar] [nvarchar](50) NULL,
	[dob] [date] NULL,
	[number] [int] NULL,
	[email] [nvarchar](50) NULL,
	[address] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LecturerInCourse]    Script Date: 28/06/2022 6:48:56 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LecturerInCourse](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[lecturerId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Slot]    Script Date: 28/06/2022 6:48:56 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Slot](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[lecturerId] [int] NULL,
	[courseId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Student]    Script Date: 28/06/2022 6:48:56 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Student](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[fullname] [nvarchar](50) NULL,
	[avatar] [varchar](1000) NULL,
	[email] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[StudentInCourse]    Script Date: 28/06/2022 6:48:56 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[StudentInCourse](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[studentId] [int] NULL,
	[courseId] [int] NULL,
	[studentResult] [nvarchar](50) NULL,
	[studentAttendance] [nvarchar](50) NULL,
	[certificate] [varchar](100) NULL,
	[termId] [int] NULL,
	[status] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Subjects]    Script Date: 28/06/2022 6:48:56 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Subjects](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[categoryId] [int] NULL,
	[name] [varchar](50) NULL,
	[code] [nvarchar](50) NULL,
	[image] [varchar](1000) NULL,
	[desc] [nvarchar](1000) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Term]    Script Date: 28/06/2022 6:48:56 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Term](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[code] [varchar](10) NULL,
	[startDate] [date] NULL,
	[endDate] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Topic]    Script Date: 28/06/2022 6:48:56 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Topic](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[subjectId] [int] NULL,
	[name] [nvarchar](50) NULL,
	[desc] [nvarchar](1000) NULL,
	[curriculumNumber] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TopicResult]    Script Date: 28/06/2022 6:48:56 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TopicResult](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[topicId] [int] NULL,
	[studentId] [int] NULL,
	[lecturerId] [int] NULL,
	[submitTime] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Account] ON 

INSERT [dbo].[Account] ([id], [email], [password], [fullname], [role], [status]) VALUES (1, N'user1@gmail.com', N'123456', N'Nguyen Van A', N'admin', 1)
INSERT [dbo].[Account] ([id], [email], [password], [fullname], [role], [status]) VALUES (2, N'user2@gmail.com', N'1234567', N'Nguyen Van B', N'student', 0)
INSERT [dbo].[Account] ([id], [email], [password], [fullname], [role], [status]) VALUES (3, N'user3@gmail.com', N'12345', N'Tran Van C', N'student', 0)
INSERT [dbo].[Account] ([id], [email], [password], [fullname], [role], [status]) VALUES (4, N'user4@gmail.com', N'12345', N'Vu Thi D', N'lecturer', 1)
INSERT [dbo].[Account] ([id], [email], [password], [fullname], [role], [status]) VALUES (5, N'user5@gmail.com', N'12345678', N'Hoang Van E', N'lecturer', 1)
INSERT [dbo].[Account] ([id], [email], [password], [fullname], [role], [status]) VALUES (6, N'user6@gmail.com', N'1234', N'Hoang Van F', N'student', 1)
INSERT [dbo].[Account] ([id], [email], [password], [fullname], [role], [status]) VALUES (7, N'user7@gmail.com', N'123456789', N'Tran Mai G', N'student', 1)
INSERT [dbo].[Account] ([id], [email], [password], [fullname], [role], [status]) VALUES (8, N'user8@gmail.com', N'1234567', N'Hoang Tuan H', N'student', 1)
INSERT [dbo].[Account] ([id], [email], [password], [fullname], [role], [status]) VALUES (9, N'user9@gmail.com', N'123', N'Mai Tuan I', N'lecturer', 0)
INSERT [dbo].[Account] ([id], [email], [password], [fullname], [role], [status]) VALUES (10, N'user10@gmail.com', N'1233', N'Do Quoc K', N'admin', 1)
INSERT [dbo].[Account] ([id], [email], [password], [fullname], [role], [status]) VALUES (11, N'user11@gmail,com', N'123333', N'Nguyen Van L', N'lecturer', 1)
SET IDENTITY_INSERT [dbo].[Account] OFF
GO
SET IDENTITY_INSERT [dbo].[Attendance] ON 

INSERT [dbo].[Attendance] ([id], [slotId], [studentInCourseId], [date], [status]) VALUES (9, 1, 3, CAST(N'2022-06-28' AS Date), N'1')
INSERT [dbo].[Attendance] ([id], [slotId], [studentInCourseId], [date], [status]) VALUES (10, 2, 4, CAST(N'2022-07-27' AS Date), N'0')
INSERT [dbo].[Attendance] ([id], [slotId], [studentInCourseId], [date], [status]) VALUES (11, 3, 5, CAST(N'2022-07-20' AS Date), N'1')
INSERT [dbo].[Attendance] ([id], [slotId], [studentInCourseId], [date], [status]) VALUES (12, 4, 6, CAST(N'2022-03-12' AS Date), N'1')
INSERT [dbo].[Attendance] ([id], [slotId], [studentInCourseId], [date], [status]) VALUES (13, 2, 7, CAST(N'2022-02-16' AS Date), N'1')
SET IDENTITY_INSERT [dbo].[Attendance] OFF
GO
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([id], [subjectId], [major]) VALUES (1, 1, N'Java')
INSERT [dbo].[Category] ([id], [subjectId], [major]) VALUES (2, 2, N'C#')
INSERT [dbo].[Category] ([id], [subjectId], [major]) VALUES (3, 3, N'.NET')
INSERT [dbo].[Category] ([id], [subjectId], [major]) VALUES (4, 4, N'ReactJS')
INSERT [dbo].[Category] ([id], [subjectId], [major]) VALUES (5, 5, N'Unity')
INSERT [dbo].[Category] ([id], [subjectId], [major]) VALUES (6, 6, N'C++')
INSERT [dbo].[Category] ([id], [subjectId], [major]) VALUES (7, 7, N'JS')
INSERT [dbo].[Category] ([id], [subjectId], [major]) VALUES (8, 8, N'Android/IOS')
SET IDENTITY_INSERT [dbo].[Category] OFF
GO
SET IDENTITY_INSERT [dbo].[Center] ON 

INSERT [dbo].[Center] ([id], [courseId], [name], [address]) VALUES (1, 1, N'Hoang Ky', N'Sai gon quan 1')
INSERT [dbo].[Center] ([id], [courseId], [name], [address]) VALUES (2, 2, N'Ly Luan', N'Sai Gon quan 2')
INSERT [dbo].[Center] ([id], [courseId], [name], [address]) VALUES (3, 3, N'My Tam', N'Binh Thanh')
INSERT [dbo].[Center] ([id], [courseId], [name], [address]) VALUES (4, 4, N'Clorua', N'Tan Phu')
SET IDENTITY_INSERT [dbo].[Center] OFF
GO
SET IDENTITY_INSERT [dbo].[Course] ON 

INSERT [dbo].[Course] ([id], [subjectId], [lecturerId], [centerId], [studentId], [joinDate], [endDate], [lecturerName], [centerName], [slot]) VALUES (4, 1, 2, 1, 1, CAST(N'2022-01-01' AS Date), CAST(N'2022-08-01' AS Date), N'Nguyen Van L', N'Hoang Ky', 1)
INSERT [dbo].[Course] ([id], [subjectId], [lecturerId], [centerId], [studentId], [joinDate], [endDate], [lecturerName], [centerName], [slot]) VALUES (5, 3, 3, 2, 5, CAST(N'2022-01-02' AS Date), CAST(N'2022-12-01' AS Date), N'Vu Thi D', N'Ly Luan', 2)
INSERT [dbo].[Course] ([id], [subjectId], [lecturerId], [centerId], [studentId], [joinDate], [endDate], [lecturerName], [centerName], [slot]) VALUES (6, 5, 4, 1, 3, CAST(N'2022-06-01' AS Date), CAST(N'2022-12-06' AS Date), N'Hoang Van E', N'Clorua', 1)
INSERT [dbo].[Course] ([id], [subjectId], [lecturerId], [centerId], [studentId], [joinDate], [endDate], [lecturerName], [centerName], [slot]) VALUES (7, 4, 5, 2, 1, CAST(N'2022-05-13' AS Date), CAST(N'2022-10-05' AS Date), N'Mai Tuan I', N'My Tam', 3)
SET IDENTITY_INSERT [dbo].[Course] OFF
GO
SET IDENTITY_INSERT [dbo].[Lecturer] ON 

INSERT [dbo].[Lecturer] ([id], [fullname], [avatar], [dob], [number], [email], [address]) VALUES (2, N'Nguyen Van L', NULL, CAST(N'1984-08-13' AS Date), 1227632978, N'user11@gmail.com', N'Ba Ria 13')
INSERT [dbo].[Lecturer] ([id], [fullname], [avatar], [dob], [number], [email], [address]) VALUES (3, N'Vu Thi D', NULL, CAST(N'1960-06-10' AS Date), 214554854, N'user4@gmail.com', N'Sai Gon 14')
INSERT [dbo].[Lecturer] ([id], [fullname], [avatar], [dob], [number], [email], [address]) VALUES (4, N'Hoang Van E', NULL, CAST(N'1990-07-31' AS Date), 21545485, N'user5@gmail.com', N'Binh Thanh 15')
INSERT [dbo].[Lecturer] ([id], [fullname], [avatar], [dob], [number], [email], [address]) VALUES (5, N'Mai Tuan I', NULL, CAST(N'0198-08-25' AS Date), 2154854, N'user9@gmail.com', N'Quan 1')
SET IDENTITY_INSERT [dbo].[Lecturer] OFF
GO
SET IDENTITY_INSERT [dbo].[LecturerInCourse] ON 

INSERT [dbo].[LecturerInCourse] ([id], [lecturerId]) VALUES (1, 1)
INSERT [dbo].[LecturerInCourse] ([id], [lecturerId]) VALUES (2, 2)
INSERT [dbo].[LecturerInCourse] ([id], [lecturerId]) VALUES (3, 3)
INSERT [dbo].[LecturerInCourse] ([id], [lecturerId]) VALUES (4, 4)
INSERT [dbo].[LecturerInCourse] ([id], [lecturerId]) VALUES (5, 5)
INSERT [dbo].[LecturerInCourse] ([id], [lecturerId]) VALUES (6, 6)
INSERT [dbo].[LecturerInCourse] ([id], [lecturerId]) VALUES (7, 7)
INSERT [dbo].[LecturerInCourse] ([id], [lecturerId]) VALUES (8, 12)
SET IDENTITY_INSERT [dbo].[LecturerInCourse] OFF
GO
SET IDENTITY_INSERT [dbo].[Slot] ON 

INSERT [dbo].[Slot] ([id], [lecturerId], [courseId]) VALUES (1, 1, 1)
INSERT [dbo].[Slot] ([id], [lecturerId], [courseId]) VALUES (2, 2, 2)
INSERT [dbo].[Slot] ([id], [lecturerId], [courseId]) VALUES (3, 3, 3)
INSERT [dbo].[Slot] ([id], [lecturerId], [courseId]) VALUES (4, 4, 4)
INSERT [dbo].[Slot] ([id], [lecturerId], [courseId]) VALUES (5, 5, 5)
INSERT [dbo].[Slot] ([id], [lecturerId], [courseId]) VALUES (6, 6, 6)
SET IDENTITY_INSERT [dbo].[Slot] OFF
GO
SET IDENTITY_INSERT [dbo].[Student] ON 

INSERT [dbo].[Student] ([id], [fullname], [avatar], [email]) VALUES (1, N'Nguyen Van B', NULL, N'user2@gmail.com')
INSERT [dbo].[Student] ([id], [fullname], [avatar], [email]) VALUES (2, N'Tran Van C', NULL, N'user3@gmail.com')
INSERT [dbo].[Student] ([id], [fullname], [avatar], [email]) VALUES (3, N'Hoang Van F', NULL, N'user6@gmail.com')
INSERT [dbo].[Student] ([id], [fullname], [avatar], [email]) VALUES (4, N'Tran Mai G', NULL, N'user7@gmail.com')
INSERT [dbo].[Student] ([id], [fullname], [avatar], [email]) VALUES (5, N'Hoang Tuan H', NULL, N'user8@gmail.com')
SET IDENTITY_INSERT [dbo].[Student] OFF
GO
SET IDENTITY_INSERT [dbo].[StudentInCourse] ON 

INSERT [dbo].[StudentInCourse] ([id], [studentId], [courseId], [studentResult], [studentAttendance], [certificate], [termId], [status]) VALUES (3, 1, 4, N'pass', N'attended', NULL, 1, 1)
INSERT [dbo].[StudentInCourse] ([id], [studentId], [courseId], [studentResult], [studentAttendance], [certificate], [termId], [status]) VALUES (4, 2, 5, N'pass', N'attended', NULL, 1, 1)
INSERT [dbo].[StudentInCourse] ([id], [studentId], [courseId], [studentResult], [studentAttendance], [certificate], [termId], [status]) VALUES (5, 3, 4, N'pass', N'attended', NULL, 2, 0)
INSERT [dbo].[StudentInCourse] ([id], [studentId], [courseId], [studentResult], [studentAttendance], [certificate], [termId], [status]) VALUES (6, 4, 5, N'not pass', N'absent', NULL, 2, 1)
INSERT [dbo].[StudentInCourse] ([id], [studentId], [courseId], [studentResult], [studentAttendance], [certificate], [termId], [status]) VALUES (7, 5, 6, N'not pass', N'absent', NULL, 3, 1)
SET IDENTITY_INSERT [dbo].[StudentInCourse] OFF
GO
SET IDENTITY_INSERT [dbo].[Subjects] ON 

INSERT [dbo].[Subjects] ([id], [categoryId], [name], [code], [image], [desc]) VALUES (1, 1, N'Basic Cross-Platform Application Programming ', N'PRN211', NULL, N'lap trinh .Net')
INSERT [dbo].[Subjects] ([id], [categoryId], [name], [code], [image], [desc]) VALUES (2, 2, N'Object-Oriented Programming', N'PRO192 ', NULL, N'lap trinh huong doi tuong')
INSERT [dbo].[Subjects] ([id], [categoryId], [name], [code], [image], [desc]) VALUES (3, 3, N'Java Web application development', N'PRJ301 ', NULL, N'lap trinh web')
INSERT [dbo].[Subjects] ([id], [categoryId], [name], [code], [image], [desc]) VALUES (4, 4, N'Programming Fundamentals', N'PRF192', NULL, N'lap trinh C can ban')
INSERT [dbo].[Subjects] ([id], [categoryId], [name], [code], [image], [desc]) VALUES (5, 5, N'Computer Networking', N'NWC203c  ', NULL, N'mon gi do')
INSERT [dbo].[Subjects] ([id], [categoryId], [name], [code], [image], [desc]) VALUES (6, 6, N'Internet of Things', N'IOT102  ', NULL, N'internet of thing')
INSERT [dbo].[Subjects] ([id], [categoryId], [name], [code], [image], [desc]) VALUES (7, 7, N'Mobile Programming', N'PRM392', NULL, N'lap trinh mobile')
INSERT [dbo].[Subjects] ([id], [categoryId], [name], [code], [image], [desc]) VALUES (12, 8, N'Data Structures and Algorithms', N'CSD201 ', NULL, N'Co so du lieu')
SET IDENTITY_INSERT [dbo].[Subjects] OFF
GO
SET IDENTITY_INSERT [dbo].[Term] ON 

INSERT [dbo].[Term] ([id], [code], [startDate], [endDate]) VALUES (1, N'SUM2022', CAST(N'2022-01-01' AS Date), CAST(N'2022-12-01' AS Date))
INSERT [dbo].[Term] ([id], [code], [startDate], [endDate]) VALUES (2, N'FALL2022', CAST(N'2022-02-01' AS Date), CAST(N'2022-06-12' AS Date))
INSERT [dbo].[Term] ([id], [code], [startDate], [endDate]) VALUES (3, N'SPR2022', CAST(N'2022-06-01' AS Date), CAST(N'2022-12-31' AS Date))
SET IDENTITY_INSERT [dbo].[Term] OFF
GO
SET IDENTITY_INSERT [dbo].[Topic] ON 

INSERT [dbo].[Topic] ([id], [subjectId], [name], [desc], [curriculumNumber]) VALUES (1, 1, N'LINQ', N'how to use LINQ', 3)
INSERT [dbo].[Topic] ([id], [subjectId], [name], [desc], [curriculumNumber]) VALUES (2, 2, N'ADO', N'how to use ADO', 4)
INSERT [dbo].[Topic] ([id], [subjectId], [name], [desc], [curriculumNumber]) VALUES (3, 3, N'3NF', N'3NF ', 3)
INSERT [dbo].[Topic] ([id], [subjectId], [name], [desc], [curriculumNumber]) VALUES (4, 4, N'Figma', N'how to design with figma', 5)
INSERT [dbo].[Topic] ([id], [subjectId], [name], [desc], [curriculumNumber]) VALUES (5, 5, N'Adruino', N'know about internet of thing', 3)
INSERT [dbo].[Topic] ([id], [subjectId], [name], [desc], [curriculumNumber]) VALUES (6, 6, N'Flutter', N'how to use flutter', 4)
INSERT [dbo].[Topic] ([id], [subjectId], [name], [desc], [curriculumNumber]) VALUES (7, 7, N'Database', N'how to use SQL', 5)
INSERT [dbo].[Topic] ([id], [subjectId], [name], [desc], [curriculumNumber]) VALUES (8, 12, N'OOP', N'OOP', 3)
SET IDENTITY_INSERT [dbo].[Topic] OFF
GO
SET IDENTITY_INSERT [dbo].[TopicResult] ON 

INSERT [dbo].[TopicResult] ([id], [topicId], [studentId], [lecturerId], [submitTime]) VALUES (1, 1, 1, 2, N'2022-12-01 ( 07:30 AM)')
INSERT [dbo].[TopicResult] ([id], [topicId], [studentId], [lecturerId], [submitTime]) VALUES (2, 2, 2, 4, N'2022-11-03 (17:56 PM )')
INSERT [dbo].[TopicResult] ([id], [topicId], [studentId], [lecturerId], [submitTime]) VALUES (3, 3, 4, 3, N'2022-01-01(22:01 PM)')
INSERT [dbo].[TopicResult] ([id], [topicId], [studentId], [lecturerId], [submitTime]) VALUES (4, 4, 5, 5, N'2022-12-31(00:00PM)')
INSERT [dbo].[TopicResult] ([id], [topicId], [studentId], [lecturerId], [submitTime]) VALUES (5, 5, 3, 2, N'2022-11-05(16:44PM)')
SET IDENTITY_INSERT [dbo].[TopicResult] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Account__AB6E6164388170EE]    Script Date: 28/06/2022 6:48:56 CH ******/
ALTER TABLE [dbo].[Account] ADD UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Attendance]  WITH CHECK ADD FOREIGN KEY([slotId])
REFERENCES [dbo].[Slot] ([id])
GO
ALTER TABLE [dbo].[Attendance]  WITH CHECK ADD FOREIGN KEY([studentInCourseId])
REFERENCES [dbo].[StudentInCourse] ([id])
GO
ALTER TABLE [dbo].[Course]  WITH CHECK ADD FOREIGN KEY([centerId])
REFERENCES [dbo].[Center] ([id])
GO
ALTER TABLE [dbo].[Course]  WITH CHECK ADD FOREIGN KEY([lecturerId])
REFERENCES [dbo].[LecturerInCourse] ([id])
GO
ALTER TABLE [dbo].[Course]  WITH CHECK ADD FOREIGN KEY([subjectId])
REFERENCES [dbo].[Subjects] ([id])
GO
ALTER TABLE [dbo].[Student]  WITH CHECK ADD FOREIGN KEY([email])
REFERENCES [dbo].[Account] ([email])
GO
ALTER TABLE [dbo].[StudentInCourse]  WITH CHECK ADD FOREIGN KEY([courseId])
REFERENCES [dbo].[Course] ([id])
GO
ALTER TABLE [dbo].[StudentInCourse]  WITH CHECK ADD FOREIGN KEY([studentId])
REFERENCES [dbo].[Student] ([id])
GO
ALTER TABLE [dbo].[Subjects]  WITH CHECK ADD FOREIGN KEY([categoryId])
REFERENCES [dbo].[Category] ([id])
GO
ALTER TABLE [dbo].[Topic]  WITH CHECK ADD FOREIGN KEY([subjectId])
REFERENCES [dbo].[Subjects] ([id])
GO
ALTER TABLE [dbo].[TopicResult]  WITH CHECK ADD FOREIGN KEY([topicId])
REFERENCES [dbo].[Topic] ([id])
GO
USE [master]
GO
ALTER DATABASE [School_Management] SET  READ_WRITE 
GO

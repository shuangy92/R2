--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.1
-- Dumped by pg_dump version 9.5.2

-- Started on 2016-04-29 09:26:55

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 42 (class 2615 OID 24794)
-- Name: ss1604c200_rd2; Type: SCHEMA; Schema: -; Owner: ss1604c200
--

CREATE SCHEMA ss1604c200_rd2;


ALTER SCHEMA ss1604c200_rd2 OWNER TO ss1604c200;

SET search_path = ss1604c200_rd2, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 469 (class 1259 OID 78995)
-- Name: contract; Type: TABLE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE TABLE contract (
    contract_id bigint NOT NULL,
    end_date date,
    pay_rate character varying(255),
    salary integer,
    start_date date,
    department_id bigint,
    job_id bigint,
    user_id bigint
);


ALTER TABLE contract OWNER TO ss1604c200;

--
-- TOC entry 468 (class 1259 OID 78993)
-- Name: contract_contract_id_seq; Type: SEQUENCE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE SEQUENCE contract_contract_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE contract_contract_id_seq OWNER TO ss1604c200;

--
-- TOC entry 3061 (class 0 OID 0)
-- Dependencies: 468
-- Name: contract_contract_id_seq; Type: SEQUENCE OWNED BY; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER SEQUENCE contract_contract_id_seq OWNED BY contract.contract_id;


--
-- TOC entry 471 (class 1259 OID 79003)
-- Name: department; Type: TABLE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE TABLE department (
    department_id bigint NOT NULL,
    location character varying(255),
    name character varying(255),
    manager bigint
);


ALTER TABLE department OWNER TO ss1604c200;

--
-- TOC entry 470 (class 1259 OID 79001)
-- Name: department_department_id_seq; Type: SEQUENCE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE SEQUENCE department_department_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE department_department_id_seq OWNER TO ss1604c200;

--
-- TOC entry 3062 (class 0 OID 0)
-- Dependencies: 470
-- Name: department_department_id_seq; Type: SEQUENCE OWNED BY; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER SEQUENCE department_department_id_seq OWNED BY department.department_id;


--
-- TOC entry 473 (class 1259 OID 79014)
-- Name: employment; Type: TABLE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE TABLE employment (
    employment_id bigint NOT NULL,
    end_date date,
    start_date date,
    contract_id bigint,
    user_id bigint
);


ALTER TABLE employment OWNER TO ss1604c200;

--
-- TOC entry 472 (class 1259 OID 79012)
-- Name: employment_employment_id_seq; Type: SEQUENCE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE SEQUENCE employment_employment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE employment_employment_id_seq OWNER TO ss1604c200;

--
-- TOC entry 3063 (class 0 OID 0)
-- Dependencies: 472
-- Name: employment_employment_id_seq; Type: SEQUENCE OWNED BY; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER SEQUENCE employment_employment_id_seq OWNED BY employment.employment_id;


--
-- TOC entry 475 (class 1259 OID 79022)
-- Name: job; Type: TABLE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE TABLE job (
    job_id bigint NOT NULL,
    description character varying(255) NOT NULL,
    hours integer,
    requirement character varying(255) NOT NULL,
    title character varying(255)
);


ALTER TABLE job OWNER TO ss1604c200;

--
-- TOC entry 474 (class 1259 OID 79020)
-- Name: job_job_id_seq; Type: SEQUENCE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE SEQUENCE job_job_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE job_job_id_seq OWNER TO ss1604c200;

--
-- TOC entry 3064 (class 0 OID 0)
-- Dependencies: 474
-- Name: job_job_id_seq; Type: SEQUENCE OWNED BY; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER SEQUENCE job_job_id_seq OWNED BY job.job_id;


--
-- TOC entry 477 (class 1259 OID 79033)
-- Name: job_post; Type: TABLE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE TABLE job_post (
    job_post_id bigint NOT NULL,
    deadline timestamp without time zone,
    end_date date,
    post_date date,
    published boolean NOT NULL,
    salary character varying(255),
    start_date date,
    title character varying(255),
    vacancies integer,
    job_id bigint
);


ALTER TABLE job_post OWNER TO ss1604c200;

--
-- TOC entry 476 (class 1259 OID 79031)
-- Name: job_post_job_post_id_seq; Type: SEQUENCE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE SEQUENCE job_post_job_post_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE job_post_job_post_id_seq OWNER TO ss1604c200;

--
-- TOC entry 3065 (class 0 OID 0)
-- Dependencies: 476
-- Name: job_post_job_post_id_seq; Type: SEQUENCE OWNED BY; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER SEQUENCE job_post_job_post_id_seq OWNED BY job_post.job_post_id;


--
-- TOC entry 479 (class 1259 OID 79044)
-- Name: person; Type: TABLE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE TABLE person (
    user_id bigint NOT NULL,
    active boolean NOT NULL,
    email character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    password_hash character varying(255) NOT NULL,
    role character varying(255) NOT NULL,
    status character varying(255) NOT NULL,
    contract_id bigint
);


ALTER TABLE person OWNER TO ss1604c200;

--
-- TOC entry 481 (class 1259 OID 79055)
-- Name: person_profile; Type: TABLE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE TABLE person_profile (
    profile_id bigint NOT NULL,
    address character varying(255),
    phone character varying(255),
    user_id bigint
);


ALTER TABLE person_profile OWNER TO ss1604c200;

--
-- TOC entry 480 (class 1259 OID 79053)
-- Name: person_profile_profile_id_seq; Type: SEQUENCE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE SEQUENCE person_profile_profile_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE person_profile_profile_id_seq OWNER TO ss1604c200;

--
-- TOC entry 3066 (class 0 OID 0)
-- Dependencies: 480
-- Name: person_profile_profile_id_seq; Type: SEQUENCE OWNED BY; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER SEQUENCE person_profile_profile_id_seq OWNED BY person_profile.profile_id;


--
-- TOC entry 478 (class 1259 OID 79042)
-- Name: person_user_id_seq; Type: SEQUENCE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE SEQUENCE person_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE person_user_id_seq OWNER TO ss1604c200;

--
-- TOC entry 3067 (class 0 OID 0)
-- Dependencies: 478
-- Name: person_user_id_seq; Type: SEQUENCE OWNED BY; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER SEQUENCE person_user_id_seq OWNED BY person.user_id;


--
-- TOC entry 483 (class 1259 OID 79066)
-- Name: request; Type: TABLE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE TABLE request (
    request_id bigint NOT NULL,
    replier_message character varying(255),
    reply_date timestamp without time zone,
    send_date timestamp without time zone,
    sender_message character varying(255),
    status character varying(255),
    title character varying(255),
    type character varying(255),
    prev_id bigint,
    replier_id bigint,
    sender_id bigint
);


ALTER TABLE request OWNER TO ss1604c200;

--
-- TOC entry 482 (class 1259 OID 79064)
-- Name: request_request_id_seq; Type: SEQUENCE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE SEQUENCE request_request_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE request_request_id_seq OWNER TO ss1604c200;

--
-- TOC entry 3068 (class 0 OID 0)
-- Dependencies: 482
-- Name: request_request_id_seq; Type: SEQUENCE OWNED BY; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER SEQUENCE request_request_id_seq OWNED BY request.request_id;


--
-- TOC entry 484 (class 1259 OID 79075)
-- Name: roster; Type: TABLE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE TABLE roster (
    roster_id bigint NOT NULL,
    status character varying(255),
    start_date integer,
    timezone character varying(255),
    author_id bigint
);


ALTER TABLE roster OWNER TO ss1604c200;

--
-- TOC entry 485 (class 1259 OID 79083)
-- Name: schedule; Type: TABLE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE TABLE schedule (
    schedule_id bigint NOT NULL,
    start_date integer,
    timezone character varying(255),
    user_id bigint
);


ALTER TABLE schedule OWNER TO ss1604c200;

--
-- TOC entry 486 (class 1259 OID 79088)
-- Name: shift; Type: TABLE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE TABLE shift (
    shift_id bigint NOT NULL,
    check_in_time character varying(255),
    check_out_time character varying(255),
    customer_rate smallint,
    date integer,
    end_time character varying(255),
    start_time character varying(255),
    roster_id bigint,
    slot_id bigint,
    user_id bigint
);


ALTER TABLE shift OWNER TO ss1604c200;

--
-- TOC entry 487 (class 1259 OID 79096)
-- Name: staffing_request; Type: TABLE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE TABLE staffing_request (
    request_id bigint NOT NULL,
    deadline timestamp without time zone,
    end_date date,
    start_date date,
    type character varying(255),
    vacancies integer,
    job_id bigint
);


ALTER TABLE staffing_request OWNER TO ss1604c200;

--
-- TOC entry 488 (class 1259 OID 79101)
-- Name: time_slot; Type: TABLE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE TABLE time_slot (
    slot_id bigint NOT NULL,
    date integer,
    end_time character varying(255),
    occupied boolean,
    start_time character varying(255),
    schedule_id bigint,
    user_id bigint
);


ALTER TABLE time_slot OWNER TO ss1604c200;

--
-- TOC entry 2862 (class 2604 OID 78998)
-- Name: contract_id; Type: DEFAULT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY contract ALTER COLUMN contract_id SET DEFAULT nextval('contract_contract_id_seq'::regclass);


--
-- TOC entry 2863 (class 2604 OID 79006)
-- Name: department_id; Type: DEFAULT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY department ALTER COLUMN department_id SET DEFAULT nextval('department_department_id_seq'::regclass);


--
-- TOC entry 2864 (class 2604 OID 79017)
-- Name: employment_id; Type: DEFAULT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY employment ALTER COLUMN employment_id SET DEFAULT nextval('employment_employment_id_seq'::regclass);


--
-- TOC entry 2865 (class 2604 OID 79025)
-- Name: job_id; Type: DEFAULT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY job ALTER COLUMN job_id SET DEFAULT nextval('job_job_id_seq'::regclass);


--
-- TOC entry 2866 (class 2604 OID 79036)
-- Name: job_post_id; Type: DEFAULT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY job_post ALTER COLUMN job_post_id SET DEFAULT nextval('job_post_job_post_id_seq'::regclass);


--
-- TOC entry 2867 (class 2604 OID 79047)
-- Name: user_id; Type: DEFAULT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY person ALTER COLUMN user_id SET DEFAULT nextval('person_user_id_seq'::regclass);


--
-- TOC entry 2868 (class 2604 OID 79058)
-- Name: profile_id; Type: DEFAULT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY person_profile ALTER COLUMN profile_id SET DEFAULT nextval('person_profile_profile_id_seq'::regclass);


--
-- TOC entry 2869 (class 2604 OID 79069)
-- Name: request_id; Type: DEFAULT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY request ALTER COLUMN request_id SET DEFAULT nextval('request_request_id_seq'::regclass);


--
-- TOC entry 3037 (class 0 OID 78995)
-- Dependencies: 469
-- Data for Name: contract; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--

COPY contract (contract_id, end_date, pay_rate, salary, start_date, department_id, job_id, user_id) FROM stdin;
\.


--
-- TOC entry 3069 (class 0 OID 0)
-- Dependencies: 468
-- Name: contract_contract_id_seq; Type: SEQUENCE SET; Schema: ss1604c200_rd2; Owner: ss1604c200
--

SELECT pg_catalog.setval('contract_contract_id_seq', 1, false);


--
-- TOC entry 3039 (class 0 OID 79003)
-- Dependencies: 471
-- Data for Name: department; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--

COPY department (department_id, location, name, manager) FROM stdin;
\.


--
-- TOC entry 3070 (class 0 OID 0)
-- Dependencies: 470
-- Name: department_department_id_seq; Type: SEQUENCE SET; Schema: ss1604c200_rd2; Owner: ss1604c200
--

SELECT pg_catalog.setval('department_department_id_seq', 1, false);


--
-- TOC entry 3041 (class 0 OID 79014)
-- Dependencies: 473
-- Data for Name: employment; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--

COPY employment (employment_id, end_date, start_date, contract_id, user_id) FROM stdin;
\.


--
-- TOC entry 3071 (class 0 OID 0)
-- Dependencies: 472
-- Name: employment_employment_id_seq; Type: SEQUENCE SET; Schema: ss1604c200_rd2; Owner: ss1604c200
--

SELECT pg_catalog.setval('employment_employment_id_seq', 1, false);


--
-- TOC entry 3043 (class 0 OID 79022)
-- Dependencies: 475
-- Data for Name: job; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--

COPY job (job_id, description, hours, requirement, title) FROM stdin;
1	Description for job 0	40	Requirement for job 0	Job Title No. 0
2	Description for job 1	40	Requirement for job 1	Job Title No. 1
3	Description for job 2	40	Requirement for job 2	Job Title No. 2
4	Description for job 3	40	Requirement for job 3	Job Title No. 3
5	Description for job 4	40	Requirement for job 4	Job Title No. 4
6	Description for job 5	40	Requirement for job 5	Job Title No. 5
7	Description for job 6	40	Requirement for job 6	Job Title No. 6
8	Description for job 7	40	Requirement for job 7	Job Title No. 7
9	Description for job 8	40	Requirement for job 8	Job Title No. 8
10	Description for job 9	40	Requirement for job 9	Job Title No. 9
11	Description for job 10	40	Requirement for job 10	Job Title No. 10
12	Description for job 11	40	Requirement for job 11	Job Title No. 11
13	Description for job 12	40	Requirement for job 12	Job Title No. 12
14	Description for job 13	40	Requirement for job 13	Job Title No. 13
15	Description for job 14	40	Requirement for job 14	Job Title No. 14
16	Description for job 15	40	Requirement for job 15	Job Title No. 15
17	Description for job 16	40	Requirement for job 16	Job Title No. 16
18	Description for job 17	40	Requirement for job 17	Job Title No. 17
19	Description for job 18	40	Requirement for job 18	Job Title No. 18
20	Description for job 19	40	Requirement for job 19	Job Title No. 19
21	Description for job 20	40	Requirement for job 20	Job Title No. 20
22	Description for job 21	40	Requirement for job 21	Job Title No. 21
23	Description for job 22	40	Requirement for job 22	Job Title No. 22
24	Description for job 23	40	Requirement for job 23	Job Title No. 23
25	Description for job 24	40	Requirement for job 24	Job Title No. 24
26	Description for job 25	40	Requirement for job 25	Job Title No. 25
27	Description for job 26	40	Requirement for job 26	Job Title No. 26
28	Description for job 27	40	Requirement for job 27	Job Title No. 27
29	Description for job 28	40	Requirement for job 28	Job Title No. 28
30	Description for job 29	40	Requirement for job 29	Job Title No. 29
31	Description for job 30	40	Requirement for job 30	Job Title No. 30
32	Description for job 31	40	Requirement for job 31	Job Title No. 31
33	Description for job 32	40	Requirement for job 32	Job Title No. 32
34	Description for job 33	40	Requirement for job 33	Job Title No. 33
35	Description for job 34	40	Requirement for job 34	Job Title No. 34
36	Description for job 35	40	Requirement for job 35	Job Title No. 35
37	Description for job 36	40	Requirement for job 36	Job Title No. 36
38	Description for job 37	40	Requirement for job 37	Job Title No. 37
39	Description for job 38	40	Requirement for job 38	Job Title No. 38
40	Description for job 39	40	Requirement for job 39	Job Title No. 39
41	Description for job 40	40	Requirement for job 40	Job Title No. 40
42	Description for job 41	40	Requirement for job 41	Job Title No. 41
43	Description for job 42	40	Requirement for job 42	Job Title No. 42
44	Description for job 43	40	Requirement for job 43	Job Title No. 43
45	Description for job 44	40	Requirement for job 44	Job Title No. 44
46	Description for job 45	40	Requirement for job 45	Job Title No. 45
47	Description for job 46	40	Requirement for job 46	Job Title No. 46
48	Description for job 47	40	Requirement for job 47	Job Title No. 47
49	Description for job 48	40	Requirement for job 48	Job Title No. 48
50	Description for job 49	40	Requirement for job 49	Job Title No. 49
51	Description for job 50	40	Requirement for job 50	Job Title No. 50
52	Description for job 51	40	Requirement for job 51	Job Title No. 51
53	Description for job 52	40	Requirement for job 52	Job Title No. 52
54	Description for job 53	40	Requirement for job 53	Job Title No. 53
55	Description for job 54	40	Requirement for job 54	Job Title No. 54
56	Description for job 55	40	Requirement for job 55	Job Title No. 55
57	Description for job 56	40	Requirement for job 56	Job Title No. 56
58	Description for job 57	40	Requirement for job 57	Job Title No. 57
59	Description for job 58	40	Requirement for job 58	Job Title No. 58
60	Description for job 59	40	Requirement for job 59	Job Title No. 59
61	Description for job 60	40	Requirement for job 60	Job Title No. 60
62	Description for job 61	40	Requirement for job 61	Job Title No. 61
63	Description for job 62	40	Requirement for job 62	Job Title No. 62
64	Description for job 63	40	Requirement for job 63	Job Title No. 63
65	Description for job 64	40	Requirement for job 64	Job Title No. 64
66	Description for job 65	40	Requirement for job 65	Job Title No. 65
67	Description for job 66	40	Requirement for job 66	Job Title No. 66
68	Description for job 67	40	Requirement for job 67	Job Title No. 67
69	Description for job 68	40	Requirement for job 68	Job Title No. 68
70	Description for job 69	40	Requirement for job 69	Job Title No. 69
71	Description for job 70	40	Requirement for job 70	Job Title No. 70
72	Description for job 71	40	Requirement for job 71	Job Title No. 71
73	Description for job 72	40	Requirement for job 72	Job Title No. 72
74	Description for job 73	40	Requirement for job 73	Job Title No. 73
75	Description for job 74	40	Requirement for job 74	Job Title No. 74
76	Description for job 75	40	Requirement for job 75	Job Title No. 75
77	Description for job 76	40	Requirement for job 76	Job Title No. 76
78	Description for job 77	40	Requirement for job 77	Job Title No. 77
79	Description for job 78	40	Requirement for job 78	Job Title No. 78
80	Description for job 79	40	Requirement for job 79	Job Title No. 79
81	Description for job 80	40	Requirement for job 80	Job Title No. 80
82	Description for job 81	40	Requirement for job 81	Job Title No. 81
83	Description for job 82	40	Requirement for job 82	Job Title No. 82
84	Description for job 83	40	Requirement for job 83	Job Title No. 83
85	Description for job 84	40	Requirement for job 84	Job Title No. 84
86	Description for job 85	40	Requirement for job 85	Job Title No. 85
87	Description for job 86	40	Requirement for job 86	Job Title No. 86
88	Description for job 87	40	Requirement for job 87	Job Title No. 87
89	Description for job 88	40	Requirement for job 88	Job Title No. 88
90	Description for job 89	40	Requirement for job 89	Job Title No. 89
91	Description for job 90	40	Requirement for job 90	Job Title No. 90
92	Description for job 91	40	Requirement for job 91	Job Title No. 91
93	Description for job 92	40	Requirement for job 92	Job Title No. 92
94	Description for job 93	40	Requirement for job 93	Job Title No. 93
95	Description for job 94	40	Requirement for job 94	Job Title No. 94
96	Description for job 95	40	Requirement for job 95	Job Title No. 95
97	Description for job 96	40	Requirement for job 96	Job Title No. 96
98	Description for job 97	40	Requirement for job 97	Job Title No. 97
99	Description for job 98	40	Requirement for job 98	Job Title No. 98
100	Description for job 99	40	Requirement for job 99	Job Title No. 99
\.


--
-- TOC entry 3072 (class 0 OID 0)
-- Dependencies: 474
-- Name: job_job_id_seq; Type: SEQUENCE SET; Schema: ss1604c200_rd2; Owner: ss1604c200
--

SELECT pg_catalog.setval('job_job_id_seq', 100, true);


--
-- TOC entry 3045 (class 0 OID 79033)
-- Dependencies: 477
-- Data for Name: job_post; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--

COPY job_post (job_post_id, deadline, end_date, post_date, published, salary, start_date, title, vacancies, job_id) FROM stdin;
\.


--
-- TOC entry 3073 (class 0 OID 0)
-- Dependencies: 476
-- Name: job_post_job_post_id_seq; Type: SEQUENCE SET; Schema: ss1604c200_rd2; Owner: ss1604c200
--

SELECT pg_catalog.setval('job_post_job_post_id_seq', 1, false);


--
-- TOC entry 3047 (class 0 OID 79044)
-- Dependencies: 479
-- Data for Name: person; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--

COPY person (user_id, active, email, name, password_hash, role, status, contract_id) FROM stdin;
1	t	admin@gmail.com	admin	$2a$04$/A7Lyo5KnF7uyhk6QPMiPe3TRwYdbv9bqWZnbn7lejYI2dBWRBeTm	ADMIN	NORMAL	\N
\.


--
-- TOC entry 3049 (class 0 OID 79055)
-- Dependencies: 481
-- Data for Name: person_profile; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--

COPY person_profile (profile_id, address, phone, user_id) FROM stdin;
\.


--
-- TOC entry 3074 (class 0 OID 0)
-- Dependencies: 480
-- Name: person_profile_profile_id_seq; Type: SEQUENCE SET; Schema: ss1604c200_rd2; Owner: ss1604c200
--

SELECT pg_catalog.setval('person_profile_profile_id_seq', 1, false);


--
-- TOC entry 3075 (class 0 OID 0)
-- Dependencies: 478
-- Name: person_user_id_seq; Type: SEQUENCE SET; Schema: ss1604c200_rd2; Owner: ss1604c200
--

SELECT pg_catalog.setval('person_user_id_seq', 1, true);


--
-- TOC entry 3051 (class 0 OID 79066)
-- Dependencies: 483
-- Data for Name: request; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--

COPY request (request_id, replier_message, reply_date, send_date, sender_message, status, title, type, prev_id, replier_id, sender_id) FROM stdin;
\.


--
-- TOC entry 3076 (class 0 OID 0)
-- Dependencies: 482
-- Name: request_request_id_seq; Type: SEQUENCE SET; Schema: ss1604c200_rd2; Owner: ss1604c200
--

SELECT pg_catalog.setval('request_request_id_seq', 1, false);


--
-- TOC entry 3052 (class 0 OID 79075)
-- Dependencies: 484
-- Data for Name: roster; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--

COPY roster (roster_id, status, start_date, timezone, author_id) FROM stdin;
\.


--
-- TOC entry 3053 (class 0 OID 79083)
-- Dependencies: 485
-- Data for Name: schedule; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--

COPY schedule (schedule_id, start_date, timezone, user_id) FROM stdin;
\.


--
-- TOC entry 3054 (class 0 OID 79088)
-- Dependencies: 486
-- Data for Name: shift; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--

COPY shift (shift_id, check_in_time, check_out_time, customer_rate, date, end_time, start_time, roster_id, slot_id, user_id) FROM stdin;
\.


--
-- TOC entry 3055 (class 0 OID 79096)
-- Dependencies: 487
-- Data for Name: staffing_request; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--

COPY staffing_request (request_id, deadline, end_date, start_date, type, vacancies, job_id) FROM stdin;
\.


--
-- TOC entry 3056 (class 0 OID 79101)
-- Dependencies: 488
-- Data for Name: time_slot; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--

COPY time_slot (slot_id, date, end_time, occupied, start_time, schedule_id, user_id) FROM stdin;
\.


--
-- TOC entry 2871 (class 2606 OID 79000)
-- Name: contract_pkey; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY contract
    ADD CONSTRAINT contract_pkey PRIMARY KEY (contract_id);


--
-- TOC entry 2873 (class 2606 OID 79011)
-- Name: department_pkey; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY department
    ADD CONSTRAINT department_pkey PRIMARY KEY (department_id);


--
-- TOC entry 2875 (class 2606 OID 79019)
-- Name: employment_pkey; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY employment
    ADD CONSTRAINT employment_pkey PRIMARY KEY (employment_id);


--
-- TOC entry 2877 (class 2606 OID 79030)
-- Name: job_pkey; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY job
    ADD CONSTRAINT job_pkey PRIMARY KEY (job_id);


--
-- TOC entry 2879 (class 2606 OID 79041)
-- Name: job_post_pkey; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY job_post
    ADD CONSTRAINT job_post_pkey PRIMARY KEY (job_post_id);


--
-- TOC entry 2881 (class 2606 OID 79052)
-- Name: person_pkey; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY person
    ADD CONSTRAINT person_pkey PRIMARY KEY (user_id);


--
-- TOC entry 2885 (class 2606 OID 79063)
-- Name: person_profile_pkey; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY person_profile
    ADD CONSTRAINT person_profile_pkey PRIMARY KEY (profile_id);


--
-- TOC entry 2887 (class 2606 OID 79074)
-- Name: request_pkey; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY request
    ADD CONSTRAINT request_pkey PRIMARY KEY (request_id);


--
-- TOC entry 2889 (class 2606 OID 79082)
-- Name: roster_pkey; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY roster
    ADD CONSTRAINT roster_pkey PRIMARY KEY (roster_id);


--
-- TOC entry 2891 (class 2606 OID 79087)
-- Name: schedule_pkey; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY schedule
    ADD CONSTRAINT schedule_pkey PRIMARY KEY (schedule_id);


--
-- TOC entry 2893 (class 2606 OID 79095)
-- Name: shift_pkey; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY shift
    ADD CONSTRAINT shift_pkey PRIMARY KEY (shift_id);


--
-- TOC entry 2895 (class 2606 OID 79100)
-- Name: staffing_request_pkey; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY staffing_request
    ADD CONSTRAINT staffing_request_pkey PRIMARY KEY (request_id);


--
-- TOC entry 2897 (class 2606 OID 79108)
-- Name: time_slot_pkey; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY time_slot
    ADD CONSTRAINT time_slot_pkey PRIMARY KEY (slot_id);


--
-- TOC entry 2883 (class 2606 OID 79110)
-- Name: uk_fwmwi44u55bo4rvwsv0cln012; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY person
    ADD CONSTRAINT uk_fwmwi44u55bo4rvwsv0cln012 UNIQUE (email);


--
-- TOC entry 2903 (class 2606 OID 79137)
-- Name: fk_1edgpyhbavjgdvji9js7pgy2g; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY employment
    ADD CONSTRAINT fk_1edgpyhbavjgdvji9js7pgy2g FOREIGN KEY (user_id) REFERENCES person(user_id);


--
-- TOC entry 2911 (class 2606 OID 79177)
-- Name: fk_26hftg8qt6wg3ss5on3ldvfsm; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY schedule
    ADD CONSTRAINT fk_26hftg8qt6wg3ss5on3ldvfsm FOREIGN KEY (user_id) REFERENCES person(user_id);


--
-- TOC entry 2898 (class 2606 OID 79111)
-- Name: fk_53wi7uto6u54ls4rm1glbgp9p; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY contract
    ADD CONSTRAINT fk_53wi7uto6u54ls4rm1glbgp9p FOREIGN KEY (department_id) REFERENCES department(department_id);


--
-- TOC entry 2912 (class 2606 OID 79182)
-- Name: fk_5blrn8skkspayphxmo28idku2; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY shift
    ADD CONSTRAINT fk_5blrn8skkspayphxmo28idku2 FOREIGN KEY (roster_id) REFERENCES roster(roster_id);


--
-- TOC entry 2905 (class 2606 OID 79147)
-- Name: fk_7pkub49yiuat709c4w5rw3sfn; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY person
    ADD CONSTRAINT fk_7pkub49yiuat709c4w5rw3sfn FOREIGN KEY (contract_id) REFERENCES contract(contract_id);


--
-- TOC entry 2901 (class 2606 OID 79126)
-- Name: fk_arq3qy0ffohfdj1hm1p2bwhme; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY department
    ADD CONSTRAINT fk_arq3qy0ffohfdj1hm1p2bwhme FOREIGN KEY (manager) REFERENCES person(user_id);


--
-- TOC entry 2907 (class 2606 OID 79157)
-- Name: fk_ayjtfxj97j8di24uwsyem7lb6; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY request
    ADD CONSTRAINT fk_ayjtfxj97j8di24uwsyem7lb6 FOREIGN KEY (prev_id) REFERENCES request(request_id);


--
-- TOC entry 2900 (class 2606 OID 79121)
-- Name: fk_b0nlsgmn8885jfsyavlkau3cl; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY contract
    ADD CONSTRAINT fk_b0nlsgmn8885jfsyavlkau3cl FOREIGN KEY (user_id) REFERENCES person(user_id);


--
-- TOC entry 2908 (class 2606 OID 79162)
-- Name: fk_bif3vajoufyq391tcqkukpc7n; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY request
    ADD CONSTRAINT fk_bif3vajoufyq391tcqkukpc7n FOREIGN KEY (replier_id) REFERENCES person(user_id);


--
-- TOC entry 2899 (class 2606 OID 79116)
-- Name: fk_ex51lsj5ult7muto1fsb6omqx; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY contract
    ADD CONSTRAINT fk_ex51lsj5ult7muto1fsb6omqx FOREIGN KEY (job_id) REFERENCES job(job_id);


--
-- TOC entry 2910 (class 2606 OID 79172)
-- Name: fk_h8762cd5xlbjfp3viwa1xb82a; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY roster
    ADD CONSTRAINT fk_h8762cd5xlbjfp3viwa1xb82a FOREIGN KEY (author_id) REFERENCES person(user_id);


--
-- TOC entry 2904 (class 2606 OID 79142)
-- Name: fk_hrjuf48jvqbcnkmxkccqrdy4p; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY job_post
    ADD CONSTRAINT fk_hrjuf48jvqbcnkmxkccqrdy4p FOREIGN KEY (job_id) REFERENCES job(job_id);


--
-- TOC entry 2909 (class 2606 OID 79167)
-- Name: fk_je9vm5x6aa68ua5wkp6n1cv8u; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY request
    ADD CONSTRAINT fk_je9vm5x6aa68ua5wkp6n1cv8u FOREIGN KEY (sender_id) REFERENCES person(user_id);


--
-- TOC entry 2914 (class 2606 OID 79192)
-- Name: fk_pgg46b1tyw7djww5oiiisvwt7; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY shift
    ADD CONSTRAINT fk_pgg46b1tyw7djww5oiiisvwt7 FOREIGN KEY (user_id) REFERENCES person(user_id);


--
-- TOC entry 2906 (class 2606 OID 79152)
-- Name: fk_ps9ytk2qjll4yh48rlxlp97cr; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY person_profile
    ADD CONSTRAINT fk_ps9ytk2qjll4yh48rlxlp97cr FOREIGN KEY (user_id) REFERENCES person(user_id);


--
-- TOC entry 2917 (class 2606 OID 79207)
-- Name: fk_sj5lxen09y30jd133hrl5jg4p; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY time_slot
    ADD CONSTRAINT fk_sj5lxen09y30jd133hrl5jg4p FOREIGN KEY (user_id) REFERENCES person(user_id);


--
-- TOC entry 2916 (class 2606 OID 79202)
-- Name: fk_ssxyvww68pu07f7ux0lqxhh8; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY time_slot
    ADD CONSTRAINT fk_ssxyvww68pu07f7ux0lqxhh8 FOREIGN KEY (schedule_id) REFERENCES schedule(schedule_id);


--
-- TOC entry 2915 (class 2606 OID 79197)
-- Name: fk_swobie16190kn7h8iw31yltv6; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY staffing_request
    ADD CONSTRAINT fk_swobie16190kn7h8iw31yltv6 FOREIGN KEY (job_id) REFERENCES job(job_id);


--
-- TOC entry 2913 (class 2606 OID 79187)
-- Name: fk_t0c4mgu0agl213exlnthxn169; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY shift
    ADD CONSTRAINT fk_t0c4mgu0agl213exlnthxn169 FOREIGN KEY (slot_id) REFERENCES time_slot(slot_id);


--
-- TOC entry 2902 (class 2606 OID 79131)
-- Name: fk_t7uyn63ce5l3s9tjdlo0tvclk; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY employment
    ADD CONSTRAINT fk_t7uyn63ce5l3s9tjdlo0tvclk FOREIGN KEY (contract_id) REFERENCES contract(contract_id);


-- Completed on 2016-04-29 09:26:55

--
-- PostgreSQL database dump complete
--


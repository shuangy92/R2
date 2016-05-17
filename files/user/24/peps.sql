--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.1
-- Dumped by pg_dump version 9.5.2

-- Started on 2016-05-13 14:08:07

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 41 (class 2615 OID 24794)
-- Name: ss1604c200_rd2; Type: SCHEMA; Schema: -; Owner: ss1604c200
--

CREATE SCHEMA ss1604c200_rd2;


ALTER SCHEMA ss1604c200_rd2 OWNER TO ss1604c200;

SET search_path = ss1604c200_rd2, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 609 (class 1259 OID 164316)
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
    user_id bigint,
    created_date timestamp without time zone,
    applicant_id bigint,
    job_post_id bigint
);


ALTER TABLE contract OWNER TO ss1604c200;

--
-- TOC entry 608 (class 1259 OID 164314)
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
-- TOC entry 3764 (class 0 OID 0)
-- Dependencies: 608
-- Name: contract_contract_id_seq; Type: SEQUENCE OWNED BY; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER SEQUENCE contract_contract_id_seq OWNED BY contract.contract_id;


--
-- TOC entry 611 (class 1259 OID 164324)
-- Name: country; Type: TABLE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE TABLE country (
    country_id bigint NOT NULL,
    code character varying(255) NOT NULL,
    name character varying(255)
);


ALTER TABLE country OWNER TO ss1604c200;

--
-- TOC entry 610 (class 1259 OID 164322)
-- Name: country_country_id_seq; Type: SEQUENCE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE SEQUENCE country_country_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE country_country_id_seq OWNER TO ss1604c200;

--
-- TOC entry 3765 (class 0 OID 0)
-- Dependencies: 610
-- Name: country_country_id_seq; Type: SEQUENCE OWNED BY; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER SEQUENCE country_country_id_seq OWNED BY country.country_id;


--
-- TOC entry 613 (class 1259 OID 164335)
-- Name: department; Type: TABLE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE TABLE department (
    department_id bigint NOT NULL,
    location character varying(255),
    name character varying(255),
    user_id bigint
);


ALTER TABLE department OWNER TO ss1604c200;

--
-- TOC entry 612 (class 1259 OID 164333)
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
-- TOC entry 3766 (class 0 OID 0)
-- Dependencies: 612
-- Name: department_department_id_seq; Type: SEQUENCE OWNED BY; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER SEQUENCE department_department_id_seq OWNED BY department.department_id;


--
-- TOC entry 615 (class 1259 OID 164346)
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
-- TOC entry 614 (class 1259 OID 164344)
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
-- TOC entry 3767 (class 0 OID 0)
-- Dependencies: 614
-- Name: employment_employment_id_seq; Type: SEQUENCE OWNED BY; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER SEQUENCE employment_employment_id_seq OWNED BY employment.employment_id;


--
-- TOC entry 617 (class 1259 OID 164354)
-- Name: file_profile; Type: TABLE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE TABLE file_profile (
    file_id bigint NOT NULL,
    info character varying(255),
    name character varying(255) NOT NULL,
    path character varying(255) NOT NULL,
    type character varying(255),
    user_id bigint
);


ALTER TABLE file_profile OWNER TO ss1604c200;

--
-- TOC entry 616 (class 1259 OID 164352)
-- Name: file_profile_file_id_seq; Type: SEQUENCE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE SEQUENCE file_profile_file_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE file_profile_file_id_seq OWNER TO ss1604c200;

--
-- TOC entry 3768 (class 0 OID 0)
-- Dependencies: 616
-- Name: file_profile_file_id_seq; Type: SEQUENCE OWNED BY; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER SEQUENCE file_profile_file_id_seq OWNED BY file_profile.file_id;


--
-- TOC entry 619 (class 1259 OID 164365)
-- Name: job; Type: TABLE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE TABLE job (
    job_id bigint NOT NULL,
    description character varying(255),
    hours integer,
    requirement character varying(255),
    title character varying(255) NOT NULL,
    department_id bigint,
    job_category_id bigint
);


ALTER TABLE job OWNER TO ss1604c200;

--
-- TOC entry 621 (class 1259 OID 164376)
-- Name: job_category; Type: TABLE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE TABLE job_category (
    job_category_id bigint NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE job_category OWNER TO ss1604c200;

--
-- TOC entry 620 (class 1259 OID 164374)
-- Name: job_category_job_category_id_seq; Type: SEQUENCE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE SEQUENCE job_category_job_category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE job_category_job_category_id_seq OWNER TO ss1604c200;

--
-- TOC entry 3769 (class 0 OID 0)
-- Dependencies: 620
-- Name: job_category_job_category_id_seq; Type: SEQUENCE OWNED BY; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER SEQUENCE job_category_job_category_id_seq OWNED BY job_category.job_category_id;


--
-- TOC entry 618 (class 1259 OID 164363)
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
-- TOC entry 3770 (class 0 OID 0)
-- Dependencies: 618
-- Name: job_job_id_seq; Type: SEQUENCE OWNED BY; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER SEQUENCE job_job_id_seq OWNED BY job.job_id;


--
-- TOC entry 623 (class 1259 OID 164384)
-- Name: job_post; Type: TABLE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE TABLE job_post (
    job_post_id bigint NOT NULL,
    contract_length character varying(255),
    created_date timestamp without time zone,
    deadline date,
    description character varying(255),
    end_date date,
    hours integer,
    last_modified_date timestamp without time zone,
    status character varying(255),
    published boolean NOT NULL,
    requirement character varying(255),
    salary character varying(255),
    start_date date,
    title character varying(255),
    vacancies integer,
    author_id bigint,
    department_id bigint,
    job_id bigint,
    last_editor_id bigint
);


ALTER TABLE job_post OWNER TO ss1604c200;

--
-- TOC entry 622 (class 1259 OID 164382)
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
-- TOC entry 3771 (class 0 OID 0)
-- Dependencies: 622
-- Name: job_post_job_post_id_seq; Type: SEQUENCE OWNED BY; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER SEQUENCE job_post_job_post_id_seq OWNED BY job_post.job_post_id;


--
-- TOC entry 624 (class 1259 OID 164393)
-- Name: other_request; Type: TABLE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE TABLE other_request (
    request_id bigint NOT NULL
);


ALTER TABLE other_request OWNER TO ss1604c200;

--
-- TOC entry 626 (class 1259 OID 164400)
-- Name: person; Type: TABLE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE TABLE person (
    user_id bigint NOT NULL,
    active boolean NOT NULL,
    email character varying(255) NOT NULL,
    name character varying(255),
    password_hash character varying(255) NOT NULL,
    role character varying(255) NOT NULL,
    status character varying(255),
    department_id bigint
);


ALTER TABLE person OWNER TO ss1604c200;

--
-- TOC entry 627 (class 1259 OID 164409)
-- Name: person_profile; Type: TABLE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE TABLE person_profile (
    user_profile_id bigint NOT NULL,
    address character varying(255),
    birthday date,
    id_number character varying(255),
    last_modified_date timestamp without time zone,
    phone character varying(255),
    contract_id bigint,
    country_id bigint
);


ALTER TABLE person_profile OWNER TO ss1604c200;

--
-- TOC entry 625 (class 1259 OID 164398)
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
-- TOC entry 3772 (class 0 OID 0)
-- Dependencies: 625
-- Name: person_user_id_seq; Type: SEQUENCE OWNED BY; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER SEQUENCE person_user_id_seq OWNED BY person.user_id;


--
-- TOC entry 629 (class 1259 OID 164419)
-- Name: request; Type: TABLE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE TABLE request (
    dis_request_type character varying(31) NOT NULL,
    request_id bigint NOT NULL,
    replier_message character varying(255),
    reply_date timestamp without time zone,
    request_type character varying(255),
    send_date timestamp without time zone,
    sender_message character varying(255),
    status character varying(255),
    title character varying(255),
    department_id bigint,
    prev_id bigint,
    replier_id bigint,
    sender_id bigint,
    thread_id bigint
);


ALTER TABLE request OWNER TO ss1604c200;

--
-- TOC entry 628 (class 1259 OID 164417)
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
-- TOC entry 3773 (class 0 OID 0)
-- Dependencies: 628
-- Name: request_request_id_seq; Type: SEQUENCE OWNED BY; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER SEQUENCE request_request_id_seq OWNED BY request.request_id;


--
-- TOC entry 631 (class 1259 OID 164430)
-- Name: review_flow; Type: TABLE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE TABLE review_flow (
    review_flow_id bigint NOT NULL
);


ALTER TABLE review_flow OWNER TO ss1604c200;

--
-- TOC entry 630 (class 1259 OID 164428)
-- Name: review_flow_review_flow_id_seq; Type: SEQUENCE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE SEQUENCE review_flow_review_flow_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE review_flow_review_flow_id_seq OWNER TO ss1604c200;

--
-- TOC entry 3774 (class 0 OID 0)
-- Dependencies: 630
-- Name: review_flow_review_flow_id_seq; Type: SEQUENCE OWNED BY; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER SEQUENCE review_flow_review_flow_id_seq OWNED BY review_flow.review_flow_id;


--
-- TOC entry 633 (class 1259 OID 164438)
-- Name: review_response; Type: TABLE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE TABLE review_response (
    review_response_id bigint NOT NULL,
    response character varying(255),
    status character varying(255) NOT NULL,
    review_run_id bigint,
    user_id bigint
);


ALTER TABLE review_response OWNER TO ss1604c200;

--
-- TOC entry 632 (class 1259 OID 164436)
-- Name: review_response_review_response_id_seq; Type: SEQUENCE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE SEQUENCE review_response_review_response_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE review_response_review_response_id_seq OWNER TO ss1604c200;

--
-- TOC entry 3775 (class 0 OID 0)
-- Dependencies: 632
-- Name: review_response_review_response_id_seq; Type: SEQUENCE OWNED BY; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER SEQUENCE review_response_review_response_id_seq OWNED BY review_response.review_response_id;


--
-- TOC entry 635 (class 1259 OID 164449)
-- Name: review_run; Type: TABLE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE TABLE review_run (
    review_run_id bigint NOT NULL,
    deadline timestamp without time zone,
    run_number smallint,
    title character varying(255),
    review_flow_id bigint
);


ALTER TABLE review_run OWNER TO ss1604c200;

--
-- TOC entry 634 (class 1259 OID 164447)
-- Name: review_run_review_run_id_seq; Type: SEQUENCE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE SEQUENCE review_run_review_run_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE review_run_review_run_id_seq OWNER TO ss1604c200;

--
-- TOC entry 3776 (class 0 OID 0)
-- Dependencies: 634
-- Name: review_run_review_run_id_seq; Type: SEQUENCE OWNED BY; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER SEQUENCE review_run_review_run_id_seq OWNED BY review_run.review_run_id;


--
-- TOC entry 636 (class 1259 OID 164455)
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
-- TOC entry 637 (class 1259 OID 164463)
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
-- TOC entry 638 (class 1259 OID 164468)
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
-- TOC entry 639 (class 1259 OID 164476)
-- Name: staffing_request; Type: TABLE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE TABLE staffing_request (
    contract_length character varying(255),
    description character varying(255),
    end_date date,
    hours integer,
    job_title character varying(255) NOT NULL,
    requirement character varying(255),
    staff_request_type character varying(255),
    start_date date,
    vacancies integer,
    request_id bigint NOT NULL,
    job_id bigint NOT NULL
);


ALTER TABLE staffing_request OWNER TO ss1604c200;

--
-- TOC entry 641 (class 1259 OID 164486)
-- Name: thread; Type: TABLE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE TABLE thread (
    request_id bigint NOT NULL,
    count integer
);


ALTER TABLE thread OWNER TO ss1604c200;

--
-- TOC entry 640 (class 1259 OID 164484)
-- Name: thread_request_id_seq; Type: SEQUENCE; Schema: ss1604c200_rd2; Owner: ss1604c200
--

CREATE SEQUENCE thread_request_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE thread_request_id_seq OWNER TO ss1604c200;

--
-- TOC entry 3777 (class 0 OID 0)
-- Dependencies: 640
-- Name: thread_request_id_seq; Type: SEQUENCE OWNED BY; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER SEQUENCE thread_request_id_seq OWNED BY thread.request_id;


--
-- TOC entry 642 (class 1259 OID 164492)
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
-- TOC entry 3511 (class 2604 OID 164319)
-- Name: contract_id; Type: DEFAULT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY contract ALTER COLUMN contract_id SET DEFAULT nextval('contract_contract_id_seq'::regclass);


--
-- TOC entry 3512 (class 2604 OID 164327)
-- Name: country_id; Type: DEFAULT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY country ALTER COLUMN country_id SET DEFAULT nextval('country_country_id_seq'::regclass);


--
-- TOC entry 3513 (class 2604 OID 164338)
-- Name: department_id; Type: DEFAULT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY department ALTER COLUMN department_id SET DEFAULT nextval('department_department_id_seq'::regclass);


--
-- TOC entry 3514 (class 2604 OID 164349)
-- Name: employment_id; Type: DEFAULT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY employment ALTER COLUMN employment_id SET DEFAULT nextval('employment_employment_id_seq'::regclass);


--
-- TOC entry 3515 (class 2604 OID 164357)
-- Name: file_id; Type: DEFAULT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY file_profile ALTER COLUMN file_id SET DEFAULT nextval('file_profile_file_id_seq'::regclass);


--
-- TOC entry 3516 (class 2604 OID 164368)
-- Name: job_id; Type: DEFAULT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY job ALTER COLUMN job_id SET DEFAULT nextval('job_job_id_seq'::regclass);


--
-- TOC entry 3517 (class 2604 OID 164379)
-- Name: job_category_id; Type: DEFAULT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY job_category ALTER COLUMN job_category_id SET DEFAULT nextval('job_category_job_category_id_seq'::regclass);


--
-- TOC entry 3518 (class 2604 OID 164387)
-- Name: job_post_id; Type: DEFAULT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY job_post ALTER COLUMN job_post_id SET DEFAULT nextval('job_post_job_post_id_seq'::regclass);


--
-- TOC entry 3519 (class 2604 OID 164403)
-- Name: user_id; Type: DEFAULT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY person ALTER COLUMN user_id SET DEFAULT nextval('person_user_id_seq'::regclass);


--
-- TOC entry 3520 (class 2604 OID 164422)
-- Name: request_id; Type: DEFAULT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY request ALTER COLUMN request_id SET DEFAULT nextval('request_request_id_seq'::regclass);


--
-- TOC entry 3521 (class 2604 OID 164433)
-- Name: review_flow_id; Type: DEFAULT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY review_flow ALTER COLUMN review_flow_id SET DEFAULT nextval('review_flow_review_flow_id_seq'::regclass);


--
-- TOC entry 3522 (class 2604 OID 164441)
-- Name: review_response_id; Type: DEFAULT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY review_response ALTER COLUMN review_response_id SET DEFAULT nextval('review_response_review_response_id_seq'::regclass);


--
-- TOC entry 3523 (class 2604 OID 164452)
-- Name: review_run_id; Type: DEFAULT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY review_run ALTER COLUMN review_run_id SET DEFAULT nextval('review_run_review_run_id_seq'::regclass);


--
-- TOC entry 3524 (class 2604 OID 164489)
-- Name: request_id; Type: DEFAULT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY thread ALTER COLUMN request_id SET DEFAULT nextval('thread_request_id_seq'::regclass);


--
-- TOC entry 3726 (class 0 OID 164316)
-- Dependencies: 609
-- Data for Name: contract; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--



--
-- TOC entry 3778 (class 0 OID 0)
-- Dependencies: 608
-- Name: contract_contract_id_seq; Type: SEQUENCE SET; Schema: ss1604c200_rd2; Owner: ss1604c200
--

SELECT pg_catalog.setval('contract_contract_id_seq', 1, true);


--
-- TOC entry 3728 (class 0 OID 164324)
-- Dependencies: 611
-- Data for Name: country; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--

INSERT INTO country VALUES (1, 'AF', 'Afghanistan');
INSERT INTO country VALUES (2, 'AL', 'Albania');
INSERT INTO country VALUES (3, 'DZ', 'Algeria');
INSERT INTO country VALUES (4, 'DS', 'American Samoa');
INSERT INTO country VALUES (5, 'AD', 'Andorra');
INSERT INTO country VALUES (6, 'AO', 'Angola');
INSERT INTO country VALUES (7, 'AI', 'Anguilla');
INSERT INTO country VALUES (8, 'AQ', 'Antarctica');
INSERT INTO country VALUES (9, 'AG', 'Antigua and Barbuda');
INSERT INTO country VALUES (10, 'AR', 'Argentina');
INSERT INTO country VALUES (11, 'AM', 'Armenia');
INSERT INTO country VALUES (12, 'AW', 'Aruba');
INSERT INTO country VALUES (13, 'AU', 'Australia');
INSERT INTO country VALUES (14, 'AT', 'Austria');
INSERT INTO country VALUES (15, 'AZ', 'Azerbaijan');
INSERT INTO country VALUES (16, 'BS', 'Bahamas');
INSERT INTO country VALUES (17, 'BH', 'Bahrain');
INSERT INTO country VALUES (18, 'BD', 'Bangladesh');
INSERT INTO country VALUES (19, 'BB', 'Barbados');
INSERT INTO country VALUES (20, 'BY', 'Belarus');
INSERT INTO country VALUES (21, 'BE', 'Belgium');
INSERT INTO country VALUES (22, 'BZ', 'Belize');
INSERT INTO country VALUES (23, 'BJ', 'Benin');
INSERT INTO country VALUES (24, 'BM', 'Bermuda');
INSERT INTO country VALUES (25, 'BT', 'Bhutan');
INSERT INTO country VALUES (26, 'BO', 'Bolivia');
INSERT INTO country VALUES (27, 'BA', 'Bosnia and Herzegovina');
INSERT INTO country VALUES (28, 'BW', 'Botswana');
INSERT INTO country VALUES (29, 'BV', 'Bouvet Island');
INSERT INTO country VALUES (30, 'BR', 'Brazil');
INSERT INTO country VALUES (31, 'IO', 'British Indian Ocean Territory');
INSERT INTO country VALUES (32, 'BN', 'Brunei Darussalam');
INSERT INTO country VALUES (33, 'BG', 'Bulgaria');
INSERT INTO country VALUES (34, 'BF', 'Burkina Faso');
INSERT INTO country VALUES (35, 'BI', 'Burundi');
INSERT INTO country VALUES (36, 'KH', 'Cambodia');
INSERT INTO country VALUES (37, 'CM', 'Cameroon');
INSERT INTO country VALUES (38, 'CA', 'Canada');
INSERT INTO country VALUES (39, 'CV', 'Cape Verde');
INSERT INTO country VALUES (40, 'KY', 'Cayman Islands');
INSERT INTO country VALUES (41, 'CF', 'Central African Republic');
INSERT INTO country VALUES (42, 'TD', 'Chad');
INSERT INTO country VALUES (43, 'CL', 'Chile');
INSERT INTO country VALUES (44, 'CN', 'China');
INSERT INTO country VALUES (45, 'CX', 'Christmas Island');
INSERT INTO country VALUES (46, 'CC', 'Cocos (Keeling) Islands');
INSERT INTO country VALUES (47, 'CO', 'Colombia');
INSERT INTO country VALUES (48, 'KM', 'Comoros');
INSERT INTO country VALUES (49, 'CG', 'Congo');
INSERT INTO country VALUES (50, 'CK', 'Cook Islands');
INSERT INTO country VALUES (51, 'CR', 'Costa Rica');
INSERT INTO country VALUES (52, 'HR', 'Croatia (Hrvatska)');
INSERT INTO country VALUES (53, 'CU', 'Cuba');
INSERT INTO country VALUES (54, 'CY', 'Cyprus');
INSERT INTO country VALUES (55, 'CZ', 'Czech Republic');
INSERT INTO country VALUES (56, 'DK', 'Denmark');
INSERT INTO country VALUES (57, 'DJ', 'Djibouti');
INSERT INTO country VALUES (58, 'DM', 'Dominica');
INSERT INTO country VALUES (59, 'DO', 'Dominican Republic');
INSERT INTO country VALUES (60, 'TP', 'East Timor');
INSERT INTO country VALUES (61, 'EC', 'Ecuador');
INSERT INTO country VALUES (62, 'EG', 'Egypt');
INSERT INTO country VALUES (63, 'SV', 'El Salvador');
INSERT INTO country VALUES (64, 'GQ', 'Equatorial Guinea');
INSERT INTO country VALUES (65, 'ER', 'Eritrea');
INSERT INTO country VALUES (66, 'EE', 'Estonia');
INSERT INTO country VALUES (67, 'ET', 'Ethiopia');
INSERT INTO country VALUES (68, 'FK', 'Falkland Islands (Malvinas)');
INSERT INTO country VALUES (69, 'FO', 'Faroe Islands');
INSERT INTO country VALUES (70, 'FJ', 'Fiji');
INSERT INTO country VALUES (71, 'FI', 'Finland');
INSERT INTO country VALUES (72, 'FR', 'France');
INSERT INTO country VALUES (73, 'FX', 'France, Metropolitan');
INSERT INTO country VALUES (74, 'GF', 'French Guiana');
INSERT INTO country VALUES (75, 'PF', 'French Polynesia');
INSERT INTO country VALUES (76, 'TF', 'French Southern Territories');
INSERT INTO country VALUES (77, 'GA', 'Gabon');
INSERT INTO country VALUES (78, 'GM', 'Gambia');
INSERT INTO country VALUES (79, 'GE', 'Georgia');
INSERT INTO country VALUES (80, 'DE', 'Germany');
INSERT INTO country VALUES (81, 'GH', 'Ghana');
INSERT INTO country VALUES (82, 'GI', 'Gibraltar');
INSERT INTO country VALUES (83, 'GK', 'Guernsey');
INSERT INTO country VALUES (84, 'GR', 'Greece');
INSERT INTO country VALUES (85, 'GL', 'Greenland');
INSERT INTO country VALUES (86, 'GD', 'Grenada');
INSERT INTO country VALUES (87, 'GP', 'Guadeloupe');
INSERT INTO country VALUES (88, 'GU', 'Guam');
INSERT INTO country VALUES (89, 'GT', 'Guatemala');
INSERT INTO country VALUES (90, 'GN', 'Guinea');
INSERT INTO country VALUES (91, 'GW', 'Guinea-Bissau');
INSERT INTO country VALUES (92, 'GY', 'Guyana');
INSERT INTO country VALUES (93, 'HT', 'Haiti');
INSERT INTO country VALUES (94, 'HM', 'Heard and Mc Donald Islands');
INSERT INTO country VALUES (95, 'HN', 'Honduras');
INSERT INTO country VALUES (96, 'HK', 'Hong Kong');
INSERT INTO country VALUES (97, 'HU', 'Hungary');
INSERT INTO country VALUES (98, 'IS', 'Iceland');
INSERT INTO country VALUES (99, 'IN', 'India');
INSERT INTO country VALUES (100, 'IM', 'Isle of Man');
INSERT INTO country VALUES (101, 'ID', 'Indonesia');
INSERT INTO country VALUES (102, 'IR', 'Iran (Islamic Republic of)');
INSERT INTO country VALUES (103, 'IQ', 'Iraq');
INSERT INTO country VALUES (104, 'IE', 'Ireland');
INSERT INTO country VALUES (105, 'IL', 'Israel');
INSERT INTO country VALUES (106, 'IT', 'Italy');
INSERT INTO country VALUES (107, 'CI', 'Ivory Coast');
INSERT INTO country VALUES (108, 'JE', 'Jersey');
INSERT INTO country VALUES (109, 'JM', 'Jamaica');
INSERT INTO country VALUES (110, 'JP', 'Japan');
INSERT INTO country VALUES (111, 'JO', 'Jordan');
INSERT INTO country VALUES (112, 'KZ', 'Kazakhstan');
INSERT INTO country VALUES (113, 'KE', 'Kenya');
INSERT INTO country VALUES (114, 'KI', 'Kiribati');
INSERT INTO country VALUES (115, 'KP', 'Korea, Democratic People''s Republic of');
INSERT INTO country VALUES (116, 'KR', 'Korea, Republic of');
INSERT INTO country VALUES (117, 'XK', 'Kosovo');
INSERT INTO country VALUES (118, 'KW', 'Kuwait');
INSERT INTO country VALUES (119, 'KG', 'Kyrgyzstan');
INSERT INTO country VALUES (120, 'LA', 'Lao People''s Democratic Republic');
INSERT INTO country VALUES (121, 'LV', 'Latvia');
INSERT INTO country VALUES (122, 'LB', 'Lebanon');
INSERT INTO country VALUES (123, 'LS', 'Lesotho');
INSERT INTO country VALUES (124, 'LR', 'Liberia');
INSERT INTO country VALUES (125, 'LY', 'Libyan Arab Jamahiriya');
INSERT INTO country VALUES (126, 'LI', 'Liechtenstein');
INSERT INTO country VALUES (127, 'LT', 'Lithuania');
INSERT INTO country VALUES (128, 'LU', 'Luxembourg');
INSERT INTO country VALUES (129, 'MO', 'Macau');
INSERT INTO country VALUES (130, 'MK', 'Macedonia');
INSERT INTO country VALUES (131, 'MG', 'Madagascar');
INSERT INTO country VALUES (132, 'MW', 'Malawi');
INSERT INTO country VALUES (133, 'MY', 'Malaysia');
INSERT INTO country VALUES (134, 'MV', 'Maldives');
INSERT INTO country VALUES (135, 'ML', 'Mali');
INSERT INTO country VALUES (136, 'MT', 'Malta');
INSERT INTO country VALUES (137, 'MH', 'Marshall Islands');
INSERT INTO country VALUES (138, 'MQ', 'Martinique');
INSERT INTO country VALUES (139, 'MR', 'Mauritania');
INSERT INTO country VALUES (140, 'MU', 'Mauritius');
INSERT INTO country VALUES (141, 'TY', 'Mayotte');
INSERT INTO country VALUES (142, 'MX', 'Mexico');
INSERT INTO country VALUES (143, 'FM', 'Micronesia, Federated States of');
INSERT INTO country VALUES (144, 'MD', 'Moldova, Republic of');
INSERT INTO country VALUES (145, 'MC', 'Monaco');
INSERT INTO country VALUES (146, 'MN', 'Mongolia');
INSERT INTO country VALUES (147, 'ME', 'Montenegro');
INSERT INTO country VALUES (148, 'MS', 'Montserrat');
INSERT INTO country VALUES (149, 'MA', 'Morocco');
INSERT INTO country VALUES (150, 'MZ', 'Mozambique');
INSERT INTO country VALUES (151, 'MM', 'Myanmar');
INSERT INTO country VALUES (152, 'NA', 'Namibia');
INSERT INTO country VALUES (153, 'NR', 'Nauru');
INSERT INTO country VALUES (154, 'NP', 'Nepal');
INSERT INTO country VALUES (155, 'NL', 'Netherlands');
INSERT INTO country VALUES (156, 'AN', 'Netherlands Antilles');
INSERT INTO country VALUES (157, 'NC', 'New Caledonia');
INSERT INTO country VALUES (158, 'NZ', 'New Zealand');
INSERT INTO country VALUES (159, 'NI', 'Nicaragua');
INSERT INTO country VALUES (160, 'NE', 'Niger');
INSERT INTO country VALUES (161, 'NG', 'Nigeria');
INSERT INTO country VALUES (162, 'NU', 'Niue');
INSERT INTO country VALUES (163, 'NF', 'Norfolk Island');
INSERT INTO country VALUES (164, 'MP', 'Northern Mariana Islands');
INSERT INTO country VALUES (165, 'NO', 'Norway');
INSERT INTO country VALUES (166, 'OM', 'Oman');
INSERT INTO country VALUES (167, 'PK', 'Pakistan');
INSERT INTO country VALUES (168, 'PW', 'Palau');
INSERT INTO country VALUES (169, 'PS', 'Palestine');
INSERT INTO country VALUES (170, 'PA', 'Panama');
INSERT INTO country VALUES (171, 'PG', 'Papua New Guinea');
INSERT INTO country VALUES (172, 'PY', 'Paraguay');
INSERT INTO country VALUES (173, 'PE', 'Peru');
INSERT INTO country VALUES (174, 'PH', 'Philippines');
INSERT INTO country VALUES (175, 'PN', 'Pitcairn');
INSERT INTO country VALUES (176, 'PL', 'Poland');
INSERT INTO country VALUES (177, 'PT', 'Portugal');
INSERT INTO country VALUES (178, 'PR', 'Puerto Rico');
INSERT INTO country VALUES (179, 'QA', 'Qatar');
INSERT INTO country VALUES (180, 'RE', 'Reunion');
INSERT INTO country VALUES (181, 'RO', 'Romania');
INSERT INTO country VALUES (182, 'RU', 'Russian Federation');
INSERT INTO country VALUES (183, 'RW', 'Rwanda');
INSERT INTO country VALUES (184, 'KN', 'Saint Kitts and Nevis');
INSERT INTO country VALUES (185, 'LC', 'Saint Lucia');
INSERT INTO country VALUES (186, 'VC', 'Saint Vincent and the Grenadines');
INSERT INTO country VALUES (187, 'WS', 'Samoa');
INSERT INTO country VALUES (188, 'SM', 'San Marino');
INSERT INTO country VALUES (189, 'ST', 'Sao Tome and Principe');
INSERT INTO country VALUES (190, 'SA', 'Saudi Arabia');
INSERT INTO country VALUES (191, 'SN', 'Senegal');
INSERT INTO country VALUES (192, 'RS', 'Serbia');
INSERT INTO country VALUES (193, 'SC', 'Seychelles');
INSERT INTO country VALUES (194, 'SL', 'Sierra Leone');
INSERT INTO country VALUES (195, 'SG', 'Singapore');
INSERT INTO country VALUES (196, 'SK', 'Slovakia');
INSERT INTO country VALUES (197, 'SI', 'Slovenia');
INSERT INTO country VALUES (198, 'SB', 'Solomon Islands');
INSERT INTO country VALUES (199, 'SO', 'Somalia');
INSERT INTO country VALUES (200, 'ZA', 'South Africa');
INSERT INTO country VALUES (201, 'GS', 'South Georgia South Sandwich Islands');
INSERT INTO country VALUES (202, 'ES', 'Spain');
INSERT INTO country VALUES (203, 'LK', 'Sri Lanka');
INSERT INTO country VALUES (204, 'SH', 'St. Helena');
INSERT INTO country VALUES (205, 'PM', 'St. Pierre and Miquelon');
INSERT INTO country VALUES (206, 'SD', 'Sudan');
INSERT INTO country VALUES (207, 'SR', 'Suriname');
INSERT INTO country VALUES (208, 'SJ', 'Svalbard and Jan Mayen Islands');
INSERT INTO country VALUES (209, 'SZ', 'Swaziland');
INSERT INTO country VALUES (210, 'SE', 'Sweden');
INSERT INTO country VALUES (211, 'CH', 'Switzerland');
INSERT INTO country VALUES (212, 'SY', 'Syrian Arab Republic');
INSERT INTO country VALUES (213, 'TW', 'Taiwan');
INSERT INTO country VALUES (214, 'TJ', 'Tajikistan');
INSERT INTO country VALUES (215, 'TZ', 'Tanzania, United Republic of');
INSERT INTO country VALUES (216, 'TH', 'Thailand');
INSERT INTO country VALUES (217, 'TG', 'Togo');
INSERT INTO country VALUES (218, 'TK', 'Tokelau');
INSERT INTO country VALUES (219, 'TO', 'Tonga');
INSERT INTO country VALUES (220, 'TT', 'Trinidad and Tobago');
INSERT INTO country VALUES (221, 'TN', 'Tunisia');
INSERT INTO country VALUES (222, 'TR', 'Turkey');
INSERT INTO country VALUES (223, 'TM', 'Turkmenistan');
INSERT INTO country VALUES (224, 'TC', 'Turks and Caicos Islands');
INSERT INTO country VALUES (225, 'TV', 'Tuvalu');
INSERT INTO country VALUES (226, 'UG', 'Uganda');
INSERT INTO country VALUES (227, 'UA', 'Ukraine');
INSERT INTO country VALUES (228, 'AE', 'United Arab Emirates');
INSERT INTO country VALUES (229, 'GB', 'United Kingdom');
INSERT INTO country VALUES (230, 'US', 'United States');
INSERT INTO country VALUES (231, 'UM', 'United States minor outlying islands');
INSERT INTO country VALUES (232, 'UY', 'Uruguay');
INSERT INTO country VALUES (233, 'UZ', 'Uzbekistan');
INSERT INTO country VALUES (234, 'VU', 'Vanuatu');
INSERT INTO country VALUES (235, 'VA', 'Vatican City State');
INSERT INTO country VALUES (236, 'VE', 'Venezuela');
INSERT INTO country VALUES (237, 'VN', 'Vietnam');
INSERT INTO country VALUES (238, 'VG', 'Virgin Islands (British)');
INSERT INTO country VALUES (239, 'VI', 'Virgin Islands (U.S.)');
INSERT INTO country VALUES (240, 'WF', 'Wallis and Futuna Islands');
INSERT INTO country VALUES (241, 'EH', 'Western Sahara');
INSERT INTO country VALUES (242, 'YE', 'Yemen');
INSERT INTO country VALUES (243, 'YU', 'Yugoslavia');
INSERT INTO country VALUES (244, 'ZR', 'Zaire');
INSERT INTO country VALUES (245, 'ZM', 'Zambia');
INSERT INTO country VALUES (246, 'ZW', 'Zimbabwe');


--
-- TOC entry 3779 (class 0 OID 0)
-- Dependencies: 610
-- Name: country_country_id_seq; Type: SEQUENCE SET; Schema: ss1604c200_rd2; Owner: ss1604c200
--

SELECT pg_catalog.setval('country_country_id_seq', 246, true);


--
-- TOC entry 3730 (class 0 OID 164335)
-- Dependencies: 613
-- Data for Name: department; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--

INSERT INTO department VALUES (1, 'shanghai', 'Dept1', NULL);
INSERT INTO department VALUES (2, 'beijing', 'Dept2', NULL);
INSERT INTO department VALUES (3, 'shanghai', 'sales', NULL);
INSERT INTO department VALUES (4, 'shanghai', 'sales', NULL);


--
-- TOC entry 3780 (class 0 OID 0)
-- Dependencies: 612
-- Name: department_department_id_seq; Type: SEQUENCE SET; Schema: ss1604c200_rd2; Owner: ss1604c200
--

SELECT pg_catalog.setval('department_department_id_seq', 4, true);


--
-- TOC entry 3732 (class 0 OID 164346)
-- Dependencies: 615
-- Data for Name: employment; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--



--
-- TOC entry 3781 (class 0 OID 0)
-- Dependencies: 614
-- Name: employment_employment_id_seq; Type: SEQUENCE SET; Schema: ss1604c200_rd2; Owner: ss1604c200
--

SELECT pg_catalog.setval('employment_employment_id_seq', 1, false);


--
-- TOC entry 3734 (class 0 OID 164354)
-- Dependencies: 617
-- Data for Name: file_profile; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--

INSERT INTO file_profile VALUES (7, ' sdf', 'sadf', 'C:\Users\Shuang\Dropbox\R2\.\files\user\4\country.json', 'application', 4);


--
-- TOC entry 3782 (class 0 OID 0)
-- Dependencies: 616
-- Name: file_profile_file_id_seq; Type: SEQUENCE SET; Schema: ss1604c200_rd2; Owner: ss1604c200
--

SELECT pg_catalog.setval('file_profile_file_id_seq', 7, true);


--
-- TOC entry 3736 (class 0 OID 164365)
-- Dependencies: 619
-- Data for Name: job; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--

INSERT INTO job VALUES (1, 'Description for job 1', 40, 'Requirement for job 1', 'Job Title No. 1', 2, 3);
INSERT INTO job VALUES (2, 'Description for job 2', 40, 'Requirement for job 2', 'Job Title No. 2', 2, 4);
INSERT INTO job VALUES (3, 'Description for job 3', 40, 'Requirement for job 3', 'Job Title No. 3', 2, 3);
INSERT INTO job VALUES (4, 'Description for job 4', 40, 'Requirement for job 4', 'Job Title No. 4', 2, 1);
INSERT INTO job VALUES (5, 'Description for job 5', 40, 'Requirement for job 5', 'Job Title No. 5', 2, 2);
INSERT INTO job VALUES (6, 'Description for job 6', 40, 'Requirement for job 6', 'Job Title No. 6', 2, 1);
INSERT INTO job VALUES (7, 'Description for job 7', 40, 'Requirement for job 7', 'Job Title No. 7', 2, 3);
INSERT INTO job VALUES (8, 'Description for job 8', 40, 'Requirement for job 8', 'Job Title No. 8', 2, 3);
INSERT INTO job VALUES (9, 'Description for job 9', 40, 'Requirement for job 9', 'Job Title No. 9', 2, 1);
INSERT INTO job VALUES (10, 'Description for job 10', 40, 'Requirement for job 10', 'Job Title No. 10', 1, 3);
INSERT INTO job VALUES (11, 'Description for job 11', 40, 'Requirement for job 11', 'Job Title No. 11', 1, 3);
INSERT INTO job VALUES (12, 'Description for job 12', 40, 'Requirement for job 12', 'Job Title No. 12', 1, 1);
INSERT INTO job VALUES (13, 'Description for job 13', 40, 'Requirement for job 13', 'Job Title No. 13', 1, 1);
INSERT INTO job VALUES (14, 'Description for job 14', 40, 'Requirement for job 14', 'Job Title No. 14', 1, 2);
INSERT INTO job VALUES (15, 'Description for job 15', 40, 'Requirement for job 15', 'Job Title No. 15', 1, 1);
INSERT INTO job VALUES (16, 'Description for job 16', 40, 'Requirement for job 16', 'Job Title No. 16', 1, 2);
INSERT INTO job VALUES (17, 'Description for job 17', 40, 'Requirement for job 17', 'Job Title No. 17', 1, 2);
INSERT INTO job VALUES (18, 'Description for job 18', 40, 'Requirement for job 18', 'Job Title No. 18', 1, 1);
INSERT INTO job VALUES (19, 'Description for job 19', 40, 'Requirement for job 19', 'Job Title No. 19', 1, 2);
INSERT INTO job VALUES (20, 'Description for job 1', 40, 'Requirement for job 1', 'Job Title No. 1', 2, 3);


--
-- TOC entry 3738 (class 0 OID 164376)
-- Dependencies: 621
-- Data for Name: job_category; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--

INSERT INTO job_category VALUES (1, 'Sale');
INSERT INTO job_category VALUES (2, 'HR');
INSERT INTO job_category VALUES (3, 'IT');
INSERT INTO job_category VALUES (4, 'Custom Service');
INSERT INTO job_category VALUES (5, 'Marketing');


--
-- TOC entry 3783 (class 0 OID 0)
-- Dependencies: 620
-- Name: job_category_job_category_id_seq; Type: SEQUENCE SET; Schema: ss1604c200_rd2; Owner: ss1604c200
--

SELECT pg_catalog.setval('job_category_job_category_id_seq', 6, true);


--
-- TOC entry 3784 (class 0 OID 0)
-- Dependencies: 618
-- Name: job_job_id_seq; Type: SEQUENCE SET; Schema: ss1604c200_rd2; Owner: ss1604c200
--

SELECT pg_catalog.setval('job_job_id_seq', 20, true);


--
-- TOC entry 3740 (class 0 OID 164384)
-- Dependencies: 623
-- Data for Name: job_post; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--

INSERT INTO job_post VALUES (1, NULL, '2016-05-13 09:53:21.913', NULL, NULL, NULL, NULL, '2016-05-13 09:53:21.913', NULL, true, NULL, NULL, NULL, 'aaa', NULL, 4, 1, 19, 4);


--
-- TOC entry 3785 (class 0 OID 0)
-- Dependencies: 622
-- Name: job_post_job_post_id_seq; Type: SEQUENCE SET; Schema: ss1604c200_rd2; Owner: ss1604c200
--

SELECT pg_catalog.setval('job_post_job_post_id_seq', 1, true);


--
-- TOC entry 3741 (class 0 OID 164393)
-- Dependencies: 624
-- Data for Name: other_request; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--



--
-- TOC entry 3743 (class 0 OID 164400)
-- Dependencies: 626
-- Data for Name: person; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--

INSERT INTO person VALUES (1, true, 'admin@gmail.com', 'admin', '$2a$04$/A7Lyo5KnF7uyhk6QPMiPe3TRwYdbv9bqWZnbn7lejYI2dBWRBeTm', 'ADMIN', 'NORMAL', 1);
INSERT INTO person VALUES (2, true, 'manager@gmail.com', 'manager', '$2a$04$/A7Lyo5KnF7uyhk6QPMiPe3TRwYdbv9bqWZnbn7lejYI2dBWRBeTm', 'MANAGER', 'NORMAL', 1);
INSERT INTO person VALUES (3, true, 'manager2@gmail.com', 'manager', '$2a$04$/A7Lyo5KnF7uyhk6QPMiPe3TRwYdbv9bqWZnbn7lejYI2dBWRBeTm', 'MANAGER', 'NORMAL', 2);
INSERT INTO person VALUES (5, true, '1@gmail.com', 'user name no.1', '$2a$10$99iu9qMG1W3u.co7fmnnFegOrd0ezarQLU7PN8YvcR8p59jKau2FW', 'EMPLOYEE', 'NORMAL', 2);
INSERT INTO person VALUES (6, true, '2@gmail.com', 'user name no.2', '$2a$10$.Qn9BHVaYLRf4vdbrP5sf.kkJbRsOoATFStd9j0Q0kSsmYrM0VMea', 'EMPLOYEE', 'NORMAL', 2);
INSERT INTO person VALUES (7, true, '3@gmail.com', 'user name no.3', '$2a$10$oGnCi2o/sJh0tnAmA0aysu6aXwGqh6KLuuDuyy5C3z62fY5U7XPzK', 'EMPLOYEE', 'NORMAL', 2);
INSERT INTO person VALUES (8, true, '4@gmail.com', 'user name no.4', '$2a$10$2UdCZ9A6rM1zXO3wD8iW0.838dhLHCn6HZsp11pN2WH9XdZaCHSf.', 'EMPLOYEE', 'NORMAL', 2);
INSERT INTO person VALUES (9, true, '5@gmail.com', 'user name no.5', '$2a$10$Ws6uSoTpGCJzNJTeyFvTyOFIinbeeFWa5Xpv3HpIwY3gc7f5rqzJ6', 'EMPLOYEE', 'NORMAL', 2);
INSERT INTO person VALUES (10, true, '6@gmail.com', 'user name no.6', '$2a$10$VDr/fGRcP8Id6xBSEHoyWuxfhi3wWVvC/NA/JWjROFOFKwmNPnNru', 'EMPLOYEE', 'NORMAL', 2);
INSERT INTO person VALUES (11, true, '7@gmail.com', 'user name no.7', '$2a$10$jzF6g1ReIAwqHeY.7qgGxejm7dFD.hS3eEfVFTlBshrvn7ltc9dEW', 'EMPLOYEE', 'NORMAL', 2);
INSERT INTO person VALUES (12, true, '8@gmail.com', 'user name no.8', '$2a$10$u2kzWjuySsmnGswA9QjVUu4Fjd60r4wfxQpC4UhSNcoVijQfuzDIe', 'EMPLOYEE', 'NORMAL', 2);
INSERT INTO person VALUES (13, true, '9@gmail.com', 'user name no.9', '$2a$10$rVzeUBFOF2esGgxtcMuNEeA4eh4f5VvcgxuYFnqMwAWpnqEZkwiJC', 'EMPLOYEE', 'NORMAL', 2);
INSERT INTO person VALUES (14, true, '10@gmail.com', 'user name no.10', '$2a$10$SzQ843BYcZPqvHx1LUng..p9FjpI3jj2uMbMBhyeTpgz5wj2rBo8i', 'EMPLOYEE', 'NORMAL', 1);
INSERT INTO person VALUES (15, true, '11@gmail.com', 'user name no.11', '$2a$10$VcDuIbAdnp1ZtkIDC.Wpt.hCJHUI.55ye7Uyg5hHYZYTQfQqkoyGG', 'EMPLOYEE', 'NORMAL', 1);
INSERT INTO person VALUES (16, true, '12@gmail.com', 'user name no.12', '$2a$10$yIMLlBcoAZeEOHLHxqS.eejFI/AVyeLLiPqA0uA.cMbKFR7jzkYwG', 'EMPLOYEE', 'NORMAL', 1);
INSERT INTO person VALUES (17, true, '13@gmail.com', 'user name no.13', '$2a$10$x1hWiR112kqijWQXSqn24.kS6KaPnfCWqArsCQVp.kGXqlEkE.Rje', 'EMPLOYEE', 'NORMAL', 1);
INSERT INTO person VALUES (18, true, '14@gmail.com', 'user name no.14', '$2a$10$bMJmJpaeHaRUTv2SeGOvI.Lhp7arDDgvcPyGoZlHmN5TyaAjup7Pi', 'EMPLOYEE', 'NORMAL', 1);
INSERT INTO person VALUES (19, true, '15@gmail.com', 'user name no.15', '$2a$10$KdbPEhWvF.MfkatZPj.lKuoLt9mfZ6hIUoiipoDffH/sEy4OozRBK', 'EMPLOYEE', 'NORMAL', 1);
INSERT INTO person VALUES (20, true, '16@gmail.com', 'user name no.16', '$2a$10$jcszOJt62jWndgEiISp6QOPRzF7HjQ6oFUn8k7pQPWT967FVbI2OS', 'EMPLOYEE', 'NORMAL', 1);
INSERT INTO person VALUES (21, true, '17@gmail.com', 'user name no.17', '$2a$10$eJrNKRSH9Z9l8Ji3mgCL/e9M4a1mQNzAxEb9rMbX/V/akH62Jk/Me', 'EMPLOYEE', 'NORMAL', 1);
INSERT INTO person VALUES (22, true, '18@gmail.com', 'user name no.18', '$2a$10$v6ytLbmUkR1PE2bwFvRWBOj3vt3ESO4Pa1NV5Jres31lr9JBPoQae', 'EMPLOYEE', 'NORMAL', 1);
INSERT INTO person VALUES (23, true, '19@gmail.com', 'user name no.19', '$2a$10$5LOVgLklwDvhpQu99Bl2ZOcM.JuYWJEqmSs5PNM9yya3VUCf13IPi', 'EMPLOYEE', 'NORMAL', 1);
INSERT INTO person VALUES (4, true, 'c@gmail.com', 'aaaaaaaaaaaaaaa', '$2a$10$.eQEMhO/Nysog9N8Nd1mB.J.QyaEM4lHmvVbFsIoQlkuxGBtUgx4G', 'APPLICANT', NULL, NULL);


--
-- TOC entry 3744 (class 0 OID 164409)
-- Dependencies: 627
-- Data for Name: person_profile; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--

INSERT INTO person_profile VALUES (4, 'sadf', '2016-05-16', 'sadfasdf', '2016-05-13 14:07:06.724', '21344523', NULL, 4);


--
-- TOC entry 3786 (class 0 OID 0)
-- Dependencies: 625
-- Name: person_user_id_seq; Type: SEQUENCE SET; Schema: ss1604c200_rd2; Owner: ss1604c200
--

SELECT pg_catalog.setval('person_user_id_seq', 24, true);


--
-- TOC entry 3746 (class 0 OID 164419)
-- Dependencies: 629
-- Data for Name: request; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--

INSERT INTO request VALUES ('STAFFING', 1, NULL, NULL, 'STAFFING', '2016-05-13 14:03:09.164', ' ', 'PENDING', 'aa', 1, NULL, NULL, 1, NULL);


--
-- TOC entry 3787 (class 0 OID 0)
-- Dependencies: 628
-- Name: request_request_id_seq; Type: SEQUENCE SET; Schema: ss1604c200_rd2; Owner: ss1604c200
--

SELECT pg_catalog.setval('request_request_id_seq', 1, true);


--
-- TOC entry 3748 (class 0 OID 164430)
-- Dependencies: 631
-- Data for Name: review_flow; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--



--
-- TOC entry 3788 (class 0 OID 0)
-- Dependencies: 630
-- Name: review_flow_review_flow_id_seq; Type: SEQUENCE SET; Schema: ss1604c200_rd2; Owner: ss1604c200
--

SELECT pg_catalog.setval('review_flow_review_flow_id_seq', 1, false);


--
-- TOC entry 3750 (class 0 OID 164438)
-- Dependencies: 633
-- Data for Name: review_response; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--



--
-- TOC entry 3789 (class 0 OID 0)
-- Dependencies: 632
-- Name: review_response_review_response_id_seq; Type: SEQUENCE SET; Schema: ss1604c200_rd2; Owner: ss1604c200
--

SELECT pg_catalog.setval('review_response_review_response_id_seq', 1, false);


--
-- TOC entry 3752 (class 0 OID 164449)
-- Dependencies: 635
-- Data for Name: review_run; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--



--
-- TOC entry 3790 (class 0 OID 0)
-- Dependencies: 634
-- Name: review_run_review_run_id_seq; Type: SEQUENCE SET; Schema: ss1604c200_rd2; Owner: ss1604c200
--

SELECT pg_catalog.setval('review_run_review_run_id_seq', 1, false);


--
-- TOC entry 3753 (class 0 OID 164455)
-- Dependencies: 636
-- Data for Name: roster; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--



--
-- TOC entry 3754 (class 0 OID 164463)
-- Dependencies: 637
-- Data for Name: schedule; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--



--
-- TOC entry 3755 (class 0 OID 164468)
-- Dependencies: 638
-- Data for Name: shift; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--



--
-- TOC entry 3756 (class 0 OID 164476)
-- Dependencies: 639
-- Data for Name: staffing_request; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--

INSERT INTO staffing_request VALUES ('2 week', 'Description for job 18', '2016-05-30', 40, 'Job Title No. 18', 'Requirement for job 18', 'REPLACE', '2016-05-16', 18, 1, 18);


--
-- TOC entry 3758 (class 0 OID 164486)
-- Dependencies: 641
-- Data for Name: thread; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--



--
-- TOC entry 3791 (class 0 OID 0)
-- Dependencies: 640
-- Name: thread_request_id_seq; Type: SEQUENCE SET; Schema: ss1604c200_rd2; Owner: ss1604c200
--

SELECT pg_catalog.setval('thread_request_id_seq', 1, false);


--
-- TOC entry 3759 (class 0 OID 164492)
-- Dependencies: 642
-- Data for Name: time_slot; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--



--
-- TOC entry 3526 (class 2606 OID 164321)
-- Name: contract_pkey; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY contract
    ADD CONSTRAINT contract_pkey PRIMARY KEY (contract_id);


--
-- TOC entry 3528 (class 2606 OID 164332)
-- Name: country_pkey; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY country
    ADD CONSTRAINT country_pkey PRIMARY KEY (country_id);


--
-- TOC entry 3530 (class 2606 OID 164343)
-- Name: department_pkey; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY department
    ADD CONSTRAINT department_pkey PRIMARY KEY (department_id);


--
-- TOC entry 3532 (class 2606 OID 164351)
-- Name: employment_pkey; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY employment
    ADD CONSTRAINT employment_pkey PRIMARY KEY (employment_id);


--
-- TOC entry 3534 (class 2606 OID 164362)
-- Name: file_profile_pkey; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY file_profile
    ADD CONSTRAINT file_profile_pkey PRIMARY KEY (file_id);


--
-- TOC entry 3540 (class 2606 OID 164381)
-- Name: job_category_pkey; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY job_category
    ADD CONSTRAINT job_category_pkey PRIMARY KEY (job_category_id);


--
-- TOC entry 3538 (class 2606 OID 164373)
-- Name: job_pkey; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY job
    ADD CONSTRAINT job_pkey PRIMARY KEY (job_id);


--
-- TOC entry 3542 (class 2606 OID 164392)
-- Name: job_post_pkey; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY job_post
    ADD CONSTRAINT job_post_pkey PRIMARY KEY (job_post_id);


--
-- TOC entry 3544 (class 2606 OID 164397)
-- Name: other_request_pkey; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY other_request
    ADD CONSTRAINT other_request_pkey PRIMARY KEY (request_id);


--
-- TOC entry 3546 (class 2606 OID 164408)
-- Name: person_pkey; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY person
    ADD CONSTRAINT person_pkey PRIMARY KEY (user_id);


--
-- TOC entry 3550 (class 2606 OID 164416)
-- Name: person_profile_pkey; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY person_profile
    ADD CONSTRAINT person_profile_pkey PRIMARY KEY (user_profile_id);


--
-- TOC entry 3552 (class 2606 OID 164427)
-- Name: request_pkey; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY request
    ADD CONSTRAINT request_pkey PRIMARY KEY (request_id);


--
-- TOC entry 3554 (class 2606 OID 164435)
-- Name: review_flow_pkey; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY review_flow
    ADD CONSTRAINT review_flow_pkey PRIMARY KEY (review_flow_id);


--
-- TOC entry 3556 (class 2606 OID 164446)
-- Name: review_response_pkey; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY review_response
    ADD CONSTRAINT review_response_pkey PRIMARY KEY (review_response_id);


--
-- TOC entry 3558 (class 2606 OID 164454)
-- Name: review_run_pkey; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY review_run
    ADD CONSTRAINT review_run_pkey PRIMARY KEY (review_run_id);


--
-- TOC entry 3560 (class 2606 OID 164462)
-- Name: roster_pkey; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY roster
    ADD CONSTRAINT roster_pkey PRIMARY KEY (roster_id);


--
-- TOC entry 3562 (class 2606 OID 164467)
-- Name: schedule_pkey; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY schedule
    ADD CONSTRAINT schedule_pkey PRIMARY KEY (schedule_id);


--
-- TOC entry 3564 (class 2606 OID 164475)
-- Name: shift_pkey; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY shift
    ADD CONSTRAINT shift_pkey PRIMARY KEY (shift_id);


--
-- TOC entry 3566 (class 2606 OID 164483)
-- Name: staffing_request_pkey; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY staffing_request
    ADD CONSTRAINT staffing_request_pkey PRIMARY KEY (request_id);


--
-- TOC entry 3568 (class 2606 OID 164491)
-- Name: thread_pkey; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY thread
    ADD CONSTRAINT thread_pkey PRIMARY KEY (request_id);


--
-- TOC entry 3570 (class 2606 OID 164499)
-- Name: time_slot_pkey; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY time_slot
    ADD CONSTRAINT time_slot_pkey PRIMARY KEY (slot_id);


--
-- TOC entry 3548 (class 2606 OID 165303)
-- Name: uk_fwmwi44u55bo4rvwsv0cln012; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY person
    ADD CONSTRAINT uk_fwmwi44u55bo4rvwsv0cln012 UNIQUE (email);


--
-- TOC entry 3536 (class 2606 OID 164501)
-- Name: uk_mmi9faksxpoq499b9vxinb294; Type: CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY file_profile
    ADD CONSTRAINT uk_mmi9faksxpoq499b9vxinb294 UNIQUE (path);


--
-- TOC entry 3577 (class 2606 OID 164529)
-- Name: fk_1edgpyhbavjgdvji9js7pgy2g; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY employment
    ADD CONSTRAINT fk_1edgpyhbavjgdvji9js7pgy2g FOREIGN KEY (user_id) REFERENCES person(user_id);


--
-- TOC entry 3599 (class 2606 OID 164634)
-- Name: fk_26hftg8qt6wg3ss5on3ldvfsm; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY schedule
    ADD CONSTRAINT fk_26hftg8qt6wg3ss5on3ldvfsm FOREIGN KEY (user_id) REFERENCES person(user_id);


--
-- TOC entry 3575 (class 2606 OID 164504)
-- Name: fk_53wi7uto6u54ls4rm1glbgp9p; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY contract
    ADD CONSTRAINT fk_53wi7uto6u54ls4rm1glbgp9p FOREIGN KEY (department_id) REFERENCES department(department_id);


--
-- TOC entry 3602 (class 2606 OID 164639)
-- Name: fk_5blrn8skkspayphxmo28idku2; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY shift
    ADD CONSTRAINT fk_5blrn8skkspayphxmo28idku2 FOREIGN KEY (roster_id) REFERENCES roster(roster_id);


--
-- TOC entry 3572 (class 2606 OID 165183)
-- Name: fk_754k5q43gobqossjsg6ynwnj7; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY contract
    ADD CONSTRAINT fk_754k5q43gobqossjsg6ynwnj7 FOREIGN KEY (applicant_id) REFERENCES person(user_id);


--
-- TOC entry 3597 (class 2606 OID 164624)
-- Name: fk_78sqe2tb3ryos3cr2bh357eqj; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY review_run
    ADD CONSTRAINT fk_78sqe2tb3ryos3cr2bh357eqj FOREIGN KEY (review_flow_id) REFERENCES review_flow(review_flow_id);


--
-- TOC entry 3588 (class 2606 OID 164584)
-- Name: fk_979epuy4wvqn6k4hk77h380ur; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY person_profile
    ADD CONSTRAINT fk_979epuy4wvqn6k4hk77h380ur FOREIGN KEY (country_id) REFERENCES country(country_id);


--
-- TOC entry 3584 (class 2606 OID 164554)
-- Name: fk_97vrvl9jj5w5riy0lye4lsq85; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY job_post
    ADD CONSTRAINT fk_97vrvl9jj5w5riy0lye4lsq85 FOREIGN KEY (department_id) REFERENCES department(department_id);


--
-- TOC entry 3593 (class 2606 OID 164594)
-- Name: fk_ayjtfxj97j8di24uwsyem7lb6; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY request
    ADD CONSTRAINT fk_ayjtfxj97j8di24uwsyem7lb6 FOREIGN KEY (prev_id) REFERENCES request(request_id);


--
-- TOC entry 3573 (class 2606 OID 164514)
-- Name: fk_b0nlsgmn8885jfsyavlkau3cl; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY contract
    ADD CONSTRAINT fk_b0nlsgmn8885jfsyavlkau3cl FOREIGN KEY (user_id) REFERENCES person(user_id);


--
-- TOC entry 3592 (class 2606 OID 164599)
-- Name: fk_bif3vajoufyq391tcqkukpc7n; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY request
    ADD CONSTRAINT fk_bif3vajoufyq391tcqkukpc7n FOREIGN KEY (replier_id) REFERENCES person(user_id);


--
-- TOC entry 3596 (class 2606 OID 164614)
-- Name: fk_bj583pbfrgmsj5ag9t3rvlx10; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY review_response
    ADD CONSTRAINT fk_bj583pbfrgmsj5ag9t3rvlx10 FOREIGN KEY (review_run_id) REFERENCES review_run(review_run_id);


--
-- TOC entry 3589 (class 2606 OID 164579)
-- Name: fk_bt4crf233swhl3jnb62gag1yn; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY person_profile
    ADD CONSTRAINT fk_bt4crf233swhl3jnb62gag1yn FOREIGN KEY (contract_id) REFERENCES contract(contract_id);


--
-- TOC entry 3580 (class 2606 OID 164544)
-- Name: fk_dksbh8tte0cvu5umax4b99keu; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY job
    ADD CONSTRAINT fk_dksbh8tte0cvu5umax4b99keu FOREIGN KEY (job_category_id) REFERENCES job_category(job_category_id);


--
-- TOC entry 3603 (class 2606 OID 164659)
-- Name: fk_dq0do26yw1r1ts31jxuv4jeid; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY staffing_request
    ADD CONSTRAINT fk_dq0do26yw1r1ts31jxuv4jeid FOREIGN KEY (request_id) REFERENCES request(request_id);


--
-- TOC entry 3574 (class 2606 OID 164509)
-- Name: fk_ex51lsj5ult7muto1fsb6omqx; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY contract
    ADD CONSTRAINT fk_ex51lsj5ult7muto1fsb6omqx FOREIGN KEY (job_id) REFERENCES job(job_id);


--
-- TOC entry 3576 (class 2606 OID 164519)
-- Name: fk_f6nogxbvabvfrafxjpu4eiius; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY department
    ADD CONSTRAINT fk_f6nogxbvabvfrafxjpu4eiius FOREIGN KEY (user_id) REFERENCES person(user_id);


--
-- TOC entry 3582 (class 2606 OID 164564)
-- Name: fk_fi103qykk8i1jbyvf6bhjmb3y; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY job_post
    ADD CONSTRAINT fk_fi103qykk8i1jbyvf6bhjmb3y FOREIGN KEY (last_editor_id) REFERENCES person(user_id);


--
-- TOC entry 3598 (class 2606 OID 164629)
-- Name: fk_h8762cd5xlbjfp3viwa1xb82a; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY roster
    ADD CONSTRAINT fk_h8762cd5xlbjfp3viwa1xb82a FOREIGN KEY (author_id) REFERENCES person(user_id);


--
-- TOC entry 3583 (class 2606 OID 164559)
-- Name: fk_hrjuf48jvqbcnkmxkccqrdy4p; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY job_post
    ADD CONSTRAINT fk_hrjuf48jvqbcnkmxkccqrdy4p FOREIGN KEY (job_id) REFERENCES job(job_id);


--
-- TOC entry 3590 (class 2606 OID 164609)
-- Name: fk_ixjahuftjnbabptxjxik2vq4t; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY request
    ADD CONSTRAINT fk_ixjahuftjnbabptxjxik2vq4t FOREIGN KEY (thread_id) REFERENCES thread(request_id);


--
-- TOC entry 3581 (class 2606 OID 164539)
-- Name: fk_ixlkkjnqv75vbnqlu521fwg2h; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY job
    ADD CONSTRAINT fk_ixlkkjnqv75vbnqlu521fwg2h FOREIGN KEY (department_id) REFERENCES department(department_id);


--
-- TOC entry 3585 (class 2606 OID 164549)
-- Name: fk_j9t14thf66vv2dbubxc36fd8m; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY job_post
    ADD CONSTRAINT fk_j9t14thf66vv2dbubxc36fd8m FOREIGN KEY (author_id) REFERENCES person(user_id);


--
-- TOC entry 3591 (class 2606 OID 164604)
-- Name: fk_je9vm5x6aa68ua5wkp6n1cv8u; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY request
    ADD CONSTRAINT fk_je9vm5x6aa68ua5wkp6n1cv8u FOREIGN KEY (sender_id) REFERENCES person(user_id);


--
-- TOC entry 3586 (class 2606 OID 164569)
-- Name: fk_jtirfkbobk7pjf9eqe7ajnbrx; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY other_request
    ADD CONSTRAINT fk_jtirfkbobk7pjf9eqe7ajnbrx FOREIGN KEY (request_id) REFERENCES request(request_id);


--
-- TOC entry 3600 (class 2606 OID 164649)
-- Name: fk_pgg46b1tyw7djww5oiiisvwt7; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY shift
    ADD CONSTRAINT fk_pgg46b1tyw7djww5oiiisvwt7 FOREIGN KEY (user_id) REFERENCES person(user_id);


--
-- TOC entry 3571 (class 2606 OID 165188)
-- Name: fk_q0ve9vcqstxbfbcede3jexcqi; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY contract
    ADD CONSTRAINT fk_q0ve9vcqstxbfbcede3jexcqi FOREIGN KEY (job_post_id) REFERENCES job_post(job_post_id);


--
-- TOC entry 3587 (class 2606 OID 164574)
-- Name: fk_r2aeqqxc7uwbn48shrhmai8cx; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY person
    ADD CONSTRAINT fk_r2aeqqxc7uwbn48shrhmai8cx FOREIGN KEY (department_id) REFERENCES department(department_id);


--
-- TOC entry 3579 (class 2606 OID 164534)
-- Name: fk_rot52qi67g26o3eyr057ln2k7; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY file_profile
    ADD CONSTRAINT fk_rot52qi67g26o3eyr057ln2k7 FOREIGN KEY (user_id) REFERENCES person(user_id);


--
-- TOC entry 3595 (class 2606 OID 164619)
-- Name: fk_s85h6s7ddxhk860m62kn4e4v0; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY review_response
    ADD CONSTRAINT fk_s85h6s7ddxhk860m62kn4e4v0 FOREIGN KEY (user_id) REFERENCES person(user_id);


--
-- TOC entry 3605 (class 2606 OID 164669)
-- Name: fk_sj5lxen09y30jd133hrl5jg4p; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY time_slot
    ADD CONSTRAINT fk_sj5lxen09y30jd133hrl5jg4p FOREIGN KEY (user_id) REFERENCES person(user_id);


--
-- TOC entry 3606 (class 2606 OID 164664)
-- Name: fk_ssxyvww68pu07f7ux0lqxhh8; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY time_slot
    ADD CONSTRAINT fk_ssxyvww68pu07f7ux0lqxhh8 FOREIGN KEY (schedule_id) REFERENCES schedule(schedule_id);


--
-- TOC entry 3604 (class 2606 OID 164654)
-- Name: fk_swobie16190kn7h8iw31yltv6; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY staffing_request
    ADD CONSTRAINT fk_swobie16190kn7h8iw31yltv6 FOREIGN KEY (job_id) REFERENCES job(job_id);


--
-- TOC entry 3601 (class 2606 OID 164644)
-- Name: fk_t0c4mgu0agl213exlnthxn169; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY shift
    ADD CONSTRAINT fk_t0c4mgu0agl213exlnthxn169 FOREIGN KEY (slot_id) REFERENCES time_slot(slot_id);


--
-- TOC entry 3594 (class 2606 OID 164589)
-- Name: fk_t2n8hee6sxyfvxqq3ia9sy9mu; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY request
    ADD CONSTRAINT fk_t2n8hee6sxyfvxqq3ia9sy9mu FOREIGN KEY (department_id) REFERENCES department(department_id);


--
-- TOC entry 3578 (class 2606 OID 164524)
-- Name: fk_t7uyn63ce5l3s9tjdlo0tvclk; Type: FK CONSTRAINT; Schema: ss1604c200_rd2; Owner: ss1604c200
--

ALTER TABLE ONLY employment
    ADD CONSTRAINT fk_t7uyn63ce5l3s9tjdlo0tvclk FOREIGN KEY (contract_id) REFERENCES contract(contract_id);


-- Completed on 2016-05-13 14:08:08

--
-- PostgreSQL database dump complete
--


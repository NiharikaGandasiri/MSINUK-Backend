package com.msinuk.main.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.msinuk.main.model.UniversityDetails;
import com.msinuk.main.model.UniversityDetails_;
import com.msinuk.main.repository.UniversityDetailsRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.MapJoin;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class UniversityService {


	@Autowired
	private UniversityDetailsRepo universityRepo;
	
	@PersistenceContext
    private EntityManager entityManager;

	public List<UniversityDetails> getUniversities() {
		return this.universityRepo.findAllByOrderByRatingDesc();
	}
	public List<UniversityDetails> getWishList(String ids) {
		String[] idArr = ids.split(",");
		List<Long> idList = new ArrayList<>();
		for(String id :idArr) {
			idList.add(Long.parseLong(id));
		}
		return this.universityRepo.findAllById(idList);
	}
	public List<UniversityDetails> addUniversities() throws JsonMappingException, JsonProcessingException {
	
		String jSon ="{\r\n"
				+ "  \"Accounting and finance\": [\r\n"
				+ "    \"Accounting and Finance, MSc\",\r\n"
				+ "    \"Finance and Investment, MSc\",\r\n"
				+ "    \"Finance, MBA\",\r\n"
				+ "    \"Financial Management and Risk, MSc\",\r\n"
				+ "    \"Fintech and Finance, MSc\",\r\n"
				+ "    \"International Banking and Finance, MSc\"\r\n"
				+ "  ],\r\n"
				+ "  \"Architecture\": [\r\n"
				+ "    \"Architectural Practice, PGDip (ARB/RIBA Part 3 Exemption)\",\r\n"
				+ "    \"Architecture Part 2, MArch\",\r\n"
				+ "    \"Architecture, Landscape and Urbanism, MSc\",\r\n"
				+ "    \"Landscape Architecture, MA\",\r\n"
				+ "    \"Landscape Architecture, MLA\"\r\n"
				+ "  ],\r\n"
				+ "  \"Biology and biomedical science\": [\r\n"
				+ "    \"Biomedical Science (Online), MSc/PGDip/PGCert\",\r\n"
				+ "    \"Biotechnology, MSc\",\r\n"
				+ "    \"Haematology, MSc/PGDip/PGCert\",\r\n"
				+ "    \"Healthcare Management, MSc/PGDip/PGCert\",\r\n"
				+ "    \"Healthcare Quality Management, MSc/PGDip/PGCert\"\r\n"
				+ "  ],\r\n"
				+ "  \"Business and management\": [\r\n"
				+ "    \"Executive Master of Business Administration, MBA\",\r\n"
				+ "    \"International Business, MA\",\r\n"
				+ "    \"International Business, MBA\",\r\n"
				+ "    \"Logistics and Supply Chain Management, MA\",\r\n"
				+ "    \"Marketing Management, MBA\"\r\n"
				+ "  ],\r\n"
				+ "  \"Chemical engineering\": [\r\n"
				+ "    \"Advanced Chemical Engineering, MSc\"\r\n"
				+ "  ],\r\n"
				+ "  \"Civil engineering\": [\r\n"
				+ "    \"Civil Engineering with Industrial Practice, MSc\",\r\n"
				+ "    \"Civil Engineering, MSc\",\r\n"
				+ "    \"Water, Waste and Environmental Engineering with Industrial Practice, MSc\",\r\n"
				+ "    \"Water, Waste and Environmental Engineering, MSc\"\r\n"
				+ "  ],\r\n"
				+ "  \"Computer engineering and cybernetics\": [\r\n"
				+ "    \"Machine Intelligence, MSc\"\r\n"
				+ "  ],\r\n"
				+ "  \"Computer science\": [\r\n"
				+ "    \"Big Data and Business Intelligence, MSc\",\r\n"
				+ "    \"Computer Forensics and Cyber Security, MSc\",\r\n"
				+ "    \"Computer Science (Network Engineering), MSc\",\r\n"
				+ "    \"Computer Science, MSc\",\r\n"
				+ "    \"Computing and Information Systems, MSc\",\r\n"
				+ "    \"Data Science and its Applications, MSc\",\r\n"
				+ "    \"Data Science, MSc\",\r\n"
				+ "    \"Management of Business Information Technology, MSc\"\r\n"
				+ "  ],\r\n"
				+ "  \"Construction\": [\r\n"
				+ "    \"Construction Management and Economics, MSc\",\r\n"
				+ "    \"Construction Project Management, MSc\",\r\n"
				+ "    \"Facilities Management, MSc\",\r\n"
				+ "    \"Occupational Hygiene, MSc\",\r\n"
				+ "    \"Project Management (International) (Distance Learning), MSc\",\r\n"
				+ "    \"Real Estate Development and Investment, MSc\",\r\n"
				+ "    \"Real Estate, MSc\",\r\n"
				+ "    \"Safety, Health and Environment, MSc\",\r\n"
				+ "    \"Sustainable Building Design and Engineering, MSc\"\r\n"
				+ "  ],\r\n"
				+ "  \"Criminology\": [\r\n"
				+ "    \"Criminology and Criminal Psychology, MSc\",\r\n"
				+ "    \"Criminology, Gender and Sexualities, MSc\",\r\n"
				+ "    \"International Criminology, MA\"\r\n"
				+ "  ],\r\n"
				+ "  \"Design\": [\r\n"
				+ "    \"Design, MA\",\r\n"
				+ "    \"Web Design and Content Planning, MA\"\r\n"
				+ "  ],\r\n"
				+ "  \"Early years studies\": [\r\n"
				+ "    \"Early Years Teacher Status (Professional), PGCE\",\r\n"
				+ "    \"Early Years Teacher Status, PGCE\"\r\n"
				+ "  ],\r\n"
				+ "  \"Economics\": [\r\n"
				+ "    \"Economics, MSc\"\r\n"
				+ "  ],\r\n"
				+ "  \"Education studies\": [\r\n"
				+ "    \"Award of Institutional Credit in Learning and Teaching in Higher Education\",\r\n"
				+ "    \"Education, MA\",\r\n"
				+ "    \"Higher Education, PGCert\",\r\n"
				+ "    \"International Postgraduate Certificate in Education (Online)\"\r\n"
				+ "  ],\r\n"
				+ "  \"Electrical and electronic engineering\": [\r\n"
				+ "    \"Electrical Power Engineering with Industrial Practice, MSc\",\r\n"
				+ "    \"Electrical Power Engineering, MSc\",\r\n"
				+ "    \"Electrical and Electronic Engineering with Industrial Practice, MSc\",\r\n"
				+ "    \"Electrical and Electronic Engineering, MSc\"\r\n"
				+ "  ],\r\n"
				+ "  \"Engineering\": [\r\n"
				+ "    \"Advanced Chemical Engineering, MSc\",\r\n"
				+ "    \"Civil Engineering with Industrial Practice, MSc\",\r\n"
				+ "    \"Civil Engineering, MSc\",\r\n"
				+ "    \"Construction Management and Economics, MSc\",\r\n"
				+ "    \"Construction Project Management, MSc\",\r\n"
				+ "    \"Electrical Power Engineering with Industrial Practice, MSc\",\r\n"
				+ "    \"Electrical Power Engineering, MSc\",\r\n"
				+ "    \"Electrical and Electronic Engineering with Industrial Practice, MSc\"\r\n"
				+ "  ],\r\n"
				+ "  \"Engineering management\": [\r\n"
				+ "    \"Engineering Management with Industrial Practice, MSc\",\r\n"
				+ "    \"Engineering Management, MSc\",\r\n"
				+ "    \"Global Shipping Management, MSc\"\r\n"
				+ "  ],\r\n"
				+ "  \"English\": [\r\n"
				+ "    \"Applied Linguistics, MA\",\r\n"
				+ "    \"Creative Writing, MA\",\r\n"
				+ "    \"English Literary London, MA\"\r\n"
				+ "  ],\r\n"
				+ "  \"Environmental sciences\": [\r\n"
				+ "    \"Agriculture for Sustainable Development, MSc\",\r\n"
				+ "    \"Global Environmental Change, MSc\",\r\n"
				+ "    \"Natural Resources, MSc (by Research)\",\r\n"
				+ "    \"Transformative Change for Sustainable Development, MSc\"\r\n"
				+ "  ],\r\n"
				+ "  \"Film, media and sound\": [\r\n"
				+ "    \"Digital Arts, MA\",\r\n"
				+ "    \"Film Production MSc\",\r\n"
				+ "    \"Film Production, MA\",\r\n"
				+ "    \"Media and Creative Cultures, MA\",\r\n"
				+ "    \"Music and Sound Design, MA\"\r\n"
				+ "  ],\r\n"
				+ "  \"Health and social care\": [\r\n"
				+ "    \"Advanced Clinical Practice, MSc\",\r\n"
				+ "    \"Enhanced Professional Practice, PGDip\",\r\n"
				+ "    \"Global Health Management, MSc\",\r\n"
				+ "    \"Global Public Health MSc\",\r\n"
				+ "    \"Healthcare Practice, MA\",\r\n"
				+ "    \"Interprofessional Healthcare Simulation, MSc\"\r\n"
				+ "  ],\r\n"
				+ "  \"Human nutrition and health\": [\r\n"
				+ "    \"Applied Food Safety and Quality Management with Industrial Practice, MSc\",\r\n"
				+ "    \"Applied Food Safety and Quality Management, PGDip/MSc\",\r\n"
				+ "    \"Food Innovation with Industrial Practice, MSc\",\r\n"
				+ "    \"Food Innovation, MSc\",\r\n"
				+ "    \"Food Safety and Quality Management e-learning, PGCert/PGDip/MSc\"\r\n"
				+ "  ],\r\n"
				+ "  \"Human resources\": [\r\n"
				+ "    \"Human Resource Management, MA\"\r\n"
				+ "  ],\r\n"
				+ "  \"Landscape architecture\": [\r\n"
				+ "    \"Architecture, Landscape and Urbanism, MSc\",\r\n"
				+ "    \"Landscape Architecture, MA\",\r\n"
				+ "    \"Landscape Architecture, MLA\"\r\n"
				+ "  ],\r\n"
				+ "  \"Law\": [\r\n"
				+ "    \"International & Commercial Law, LLM\",\r\n"
				+ "    \"Law, Regulation and the International Financial System, MSc\"\r\n"
				+ "  ],\r\n"
				+ "  \"Logistics and supply chain management\": [\r\n"
				+ "    \"Logistics and Supply Chain Management, MA\"\r\n"
				+ "  ],\r\n"
				+ "  \"Marketing and advertising\": [\r\n"
				+ "    \"Marketing Management, MBA\",\r\n"
				+ "    \"Strategic Advertising and Marketing Communications, MA\",\r\n"
				+ "    \"Strategic Marketing, MA\"\r\n"
				+ "  ],\r\n"
				+ "  \"Mechanical engineering\": [\r\n"
				+ "    \"Mechanical and Manufacturing Engineering with Industrial Practice, MSc\",\r\n"
				+ "    \"Mechanical and Manufacturing Engineering, MSc\",\r\n"
				+ "    \"Product Design Engineering, MSc\"\r\n"
				+ "  ],\r\n"
				+ "  \"Nursing\": [\r\n"
				+ "    \"Advanced Clinical Practice, MSc\",\r\n"
				+ "    \"Advanced Clinical Practitioner, MSc (Degree Apprenticeship)\",\r\n"
				+ "    \"Enhanced Professional Practice, PGDip\",\r\n"
				+ "    \"Healthcare Practice, MA\",\r\n"
				+ "    \"Interprofessional Healthcare Simulation, MSc\",\r\n"
				+ "    \"Nursing (Adult Nursing), MSc\"\r\n"
				+ "  ],\r\n"
				+ "  \"PE, sport and exercise science\": [\r\n"
				+ "    \"Physician Associate, MSc (Degree Apprenticeship)\",\r\n"
				+ "    \"Secondary Education Physical Education, PGCE\",\r\n"
				+ "    \"Sport and Exercise Psychology, MSc\",\r\n"
				+ "    \"Strength and Conditioning, MSc\"\r\n"
				+ "  ],\r\n"
				+ "  \"Pharmaceutical science\": [\r\n"
				+ "    \"Formulation Science, MSc\",\r\n"
				+ "    \"Pharmaceutical Science, MSc/PGDip\",\r\n"
				+ "    \"Pharmaceutical Sciences with Industrial Practice, MSc\"\r\n"
				+ "  ],\r\n"
				+ "  \"Pharmacy\": [\r\n"
				+ "    \"General Pharmacy Practice, PGCert/PGDip/MSc\",\r\n"
				+ "    \"Independent/Supplementary Prescribing, PGCert\",\r\n"
				+ "    \"Medicines Optimisation, PGCert/PGDip/MSc\"\r\n"
				+ "  ],\r\n"
				+ "  \"Psychology\": [\r\n"
				+ "    \"Child and Adolescent Psychology, MSc\",\r\n"
				+ "    \"Criminology and Criminal Psychology, MSc\",\r\n"
				+ "    \"Forensic Psychology, MSc\",\r\n"
				+ "    \"Occupational Psychology, MSc\",\r\n"
				+ "    \"Psychology, MSc (Conversion Degree)\",\r\n"
				+ "    \"Sport and Exercise Psychology, MSc\",\r\n"
				+ "    \"Therapeutic Counselling, MSc\"\r\n"
				+ "  ],\r\n"
				+ "  \"Public health\": [\r\n"
				+ "    \"Global Health Management, MSc\",\r\n"
				+ "    \"Global Public Health MSc\"\r\n"
				+ "  ],\r\n"
				+ "  \"Science\": [\r\n"
				+ "    \"Agriculture for Sustainable Development, MSc\",\r\n"
				+ "    \"Applied Food Safety and Quality Management with Industrial Practice, MSc\",\r\n"
				+ "    \"Applied Food Safety and Quality Management, PGDip/MSc\",\r\n"
				+ "    \"Biomedical Science (Online), MSc/PGDip/PGCert\",\r\n"
				+ "    \"Biotechnology, MSc\",\r\n"
				+ "    \"Food Innovation with Industrial Practice, MSc\"\r\n"
				+ "  ],\r\n"
				+ "  \"Social work\": [\r\n"
				+ "    \"Social Work, PGDip/MA\"\r\n"
				+ "  ],\r\n"
				+ "  \"Teacher training\": [\r\n"
				+ "    \"Physician Associate, MSc (Degree Apprenticeship)\",\r\n"
				+ "    \"Assessment Only Route to Qualified Teacher Status\",\r\n"
				+ "    \"International Postgraduate Certificate in Education (Online)\",\r\n"
				+ "    \"PGCE Further Education & Skills Sector\",\r\n"
				+ "    \"Primary Education, PGCE\",\r\n"
				+ "    \"Primary Mathematics (Subject Specialist), PGCE\"\r\n"
				+ "  ],\r\n"
				+ "  \"Tourism, hospitality and events\": [\r\n"
				+ "    \"International Events Management, MA\",\r\n"
				+ "    \"International Tourism and Hospitality Management, MA\"\r\n"
				+ "  ]\r\n"
				+ "}";
		ObjectMapper mapper = new ObjectMapper();
	    mapper.enable(SerializationFeature.INDENT_OUTPUT);
	    @SuppressWarnings("unchecked")
	    Map <String, String[]> courses = mapper.readValue(jSon, new TypeReference<Map<String,String[]>>(){});
		String description= "The University of Greenwich is a public university located in London and Kent, United Kingdom. Previous names include Woolwich Polytechnic and Thames Polytechnic. The university's main campus is at the Old Royal Naval College, which along with its Avery Hill campus, is located in the Royal Borough of Greenwich."
				+ "The university's range of subjects includes architecture, business, computing, mathematics, education, engineering, humanities, maritime studies, natural sciences, pharmacy and social sciences.";
		String[] images = {"UniversityofGreenwich.jpg","UniversityofGreenwich1.jpg","UniversityofGreenwich2.jpg","UniversityofGreenwich3.jpg"};
		String reviews = "https://www.google.com/maps/place/University+of+Greenwich/@51.4822266,-0.008234,17z/data=!4m12!1m2!2m1!1sUniversity+of+Greenwich!3m8!1s0x487602844a443523:0x9f5181128b65430a!8m2!3d51.4829866!4d-0.0063883!9m1!1b1!15sChdVbml2ZXJzaXR5IG9mIEdyZWVud2ljaJIBCnVuaXZlcnNpdHngAQA!16zL20vMDJtZ2R0?entry=ttu";
		String courseMap = "{\r\n"
				+ "  \"commonurl\": \"https://www.gre.ac.uk/postgraduate-courses/\",\r\n"
				+ "  \"Accounting and Finance, MSc\": \"bus/accfin/\",\r\n"
				+ "  \"Finance and Investment, MSc\": \"bus/finmaninv/\",\r\n"
				+ "  \"Finance, MBA\": \"bus/finance-mba/\",\r\n"
				+ "  \"Financial Management and Risk, MSc\": \"bus/finman/\",\r\n"
				+ "  \"Fintech and Finance, MSc\": \"bus/fintech-and-finance-msc/\",\r\n"
				+ "  \"International Banking and Finance, MSc\": \"bus/ibf/\",\r\n"
				+ "  \"Architectural Practice, PGDip (ARB/RIBA Part 3 Exemption)\": \"ach/architectural-practice-pgdip-arb-riba-part-3-exemption/\",\r\n"
				+ "  \"Architecture Part 2, MArch\": \"ach/architecture-part-2-march/\",\r\n"
				+ "  \"Architecture, Landscape and Urbanism, MSc\": \"ach/architecture-landscape-and-urbanism-msc/\",\r\n"
				+ "  \"Landscape Architecture, MA\": \"MA  ach/landscape-architecture-ma/\",\r\n"
				+ "  \"Landscape Architecture, MLA\": \"ach/landscape-architecture-mla/\",\r\n"
				+ "  \"Biomedical Science (Online), MSc/PGDip/PGCert\": \"engsci/bio/\",\r\n"
				+ "  \"Biotechnology, MSc\": \"engsci/biotech/\",\r\n"
				+ "  \"Haematology, MSc/PGDip/PGCert\": \"engsci/haematology-msc/\",\r\n"
				+ "  \"Healthcare Management, MSc/PGDip/PGCert\": \"engsci/healthcare-management-msc/\",\r\n"
				+ "  \"Healthcare Quality Management, MSc/PGDip/PGCert\": \"engsci/healthcare-quality-management-msc/\",\r\n"
				+ "  \"Executive Master of Business Administration, MBA\": \"bus/execmba/\",\r\n"
				+ "  \"International Business, MA\": \"bus/intbus/\",\r\n"
				+ "  \"International Business, MBA\": \"bus/intmba/\",\r\n"
				+ "  \"Logistics and Supply Chain Management, MA\": \"bus/lscm/\",\r\n"
				+ "  \"Marketing Management, MBA\": \"bus/mktmgt-mba/\",\r\n"
				+ "  \"Advanced Chemical Engineering, MSc\": \"engsci/advanced-chemical-engineering-msc/\",\r\n"
				+ "  \"Civil Engineering with Industrial Practice, MSc\": \"engsci/civil-engineering-with-industrial-practice-msc/\",\r\n"
				+ "  \"Civil Engineering, MSc\": \"engsci/civeng/\",\r\n"
				+ "  \"Water, Waste and Environmental Engineering with Industrial Practice, MSc\": \"engsci/water-waste-and-environmental-engineering-with-industrial-practice-msc/\",\r\n"
				+ "  \"Water, Waste and Environmental Engineering, MSc\": \"engsci/wat-was-env-eng/\",\r\n"
				+ "  \"Machine Intelligence, MSc\": \"engsci/mac-int/\",\r\n"
				+ "  \"Big Data and Business Intelligence, MSc\": \"engsci/big-data-and-business-intelligence-msc/\",\r\n"
				+ "  \"Computer Forensics and Cyber Security, MSc\": \"engsci/computer-forensics-and-cyber-security-msc/\",\r\n"
				+ "  \"Computer Science (Network Engineering), MSc\": \"engsci/computer-science-network-engineering-msc/\",\r\n"
				+ "  \"Computer Science, MSc\": \"engsci/computer-science-msc/\",\r\n"
				+ "  \"Computing and Information Systems, MSc\": \"engsci/computing-and-information-systems-msc/\",\r\n"
				+ "  \"Data Science and its Applications, MSc\": \"engsci/data-science-and-its-applications-msc/\",\r\n"
				+ "  \"Data Science, MSc\": \"engsci/computer-science-data-science-msc/\",\r\n"
				+ "  \"Management of Business Information Technology, MSc\": \"engsci/management-of-business-information-technology-msc/\",\r\n"
				+ "  \"Construction Management and Economics, MSc\": \"engsci/construction-management-and-economics-msc/\",\r\n"
				+ "  \"Construction Project Management, MSc\": \"engsci/construction-project-management-msc/\",\r\n"
				+ "  \"Facilities Management, MSc\": \"engsci/facilities-management-msc/\",\r\n"
				+ "  \"Occupational Hygiene, MSc\": \"engsci/occupational-hygiene-msc/\",\r\n"
				+ "  \"Project Management (International) (Distance Learning), MSc\": \"engsci/project-management-international-distance-learning-msc/\",\r\n"
				+ "  \"Real Estate Development and Investment, MSc\": \"engsci/real-estate-development-and-investment-msc/\",\r\n"
				+ "  \"Real Estate, MSc\": \"engsci/real-estate-msc/\",\r\n"
				+ "  \"Safety, Health and Environment, MSc\": \"engsci/safety-health-and-environment-msc/\",\r\n"
				+ "  \"Sustainable Building Design and Engineering, MSc\": \"engsci/sustainable-building-design-and-engineering-msc/\",\r\n"
				+ "  \"Criminology and Criminal Psychology, MSc\": \"ach/criminology-and-criminal-psychology-msc/\",\r\n"
				+ "  \"Criminology, Gender and Sexualities, MSc\": \"ach/criminology-gender-and-sexualities-msc/\",\r\n"
				+ "  \"International Criminology, MA\": \"ach/international-criminology-ma/\",\r\n"
				+ "  \"Design, MA\": \"ach/design-ma/\",\r\n"
				+ "  \"Web Design and Content Planning, MA\": \"ach/web-design-and-content-planning-ma/\",\r\n"
				+ "  \"Early Years Teacher Status (Professional), PGCE\": \"eduhea/eytsprof/\",\r\n"
				+ "  \"Early Years Teacher Status, PGCE\": \"eduhea/eyts/\",\r\n"
				+ "  \"Economics, MSc\": \"bus/econ/\",\r\n"
				+ "  \"Award of Institutional Credit in Learning and Teaching in Higher Education\": \"eduhea/awa-cre/\",\r\n"
				+ "  \"Education, MA\": \"eduhea/ed/\",\r\n"
				+ "  \"Higher Education, PGCert\": \"eduhea/hep/\",\r\n"
				+ "  \"International Postgraduate Certificate in Education (Online)\": \"eduhea/international-postgraduate-certificate-in-education/\",\r\n"
				+ "  \"Electrical Power Engineering with Industrial Practice, MSc\": \"engsci/electrical-power-engineering-industrial-practice-msc/\",\r\n"
				+ "  \"Electrical Power Engineering, MSc\": \"engsci/elecpow/\",\r\n"
				+ "  \"Electrical and Electronic Engineering with Industrial Practice, MSc\": \"engsci/electrical-and-electronic-engineering-with-industrial-practice-msc/\",\r\n"
				+ "  \"Electrical and Electronic Engineering, MSc\": \"engsci/elelec/\",\r\n"
				+ "  \"Engineering Management with Industrial Practice, MSc\": \"engsci/engineering-management-with-industrial-practice/\",\r\n"
				+ "  \"Engineering Management, MSc\": \"engsci/engman/\",\r\n"
				+ "  \"Global Shipping Management, MSc\": \"engsci/glo-shi/\",\r\n"
				+ "  \"Applied Linguistics, MA\": \"ach/applied-linguistics-ma/\",\r\n"
				+ "  \"Creative Writing, MA\": \"ach/creative-writing-ma/\",\r\n"
				+ "  \"English Literary London, MA\": \"ach/english-literary-london-ma/\",\r\n"
				+ "  \"Agriculture for Sustainable Development, MSc\": \"engsci/agrsustdev/\",\r\n"
				+ "  \"Global Environmental Change, MSc\": \"engsci/glob-envi-change/\",\r\n"
				+ "  \"Natural Resources, MSc (by Research)\": \"engsci/natural-resources-msc-by-research/\",\r\n"
				+ "  \"Transformative Change for Sustainable Development, MSc\": \"engsci/transformative-change-for-sustainable-development-msc/\",\r\n"
				+ "  \"Digital Arts, MA\": \"ach/ma-digital-arts/\",\r\n"
				+ "  \"Film Production MSc\": \"ach/film-production-msc/\",\r\n"
				+ "  \"Film Production, MA\": \"ach/film-production-ma/\",\r\n"
				+ "  \"Media and Creative Cultures, MA\": \"ach/ma-media-and-creative-cultures/\",\r\n"
				+ "  \"Music and Sound Design, MA\": \"ach/music-and-sound-design-ma/\",\r\n"
				+ "  \"Advanced Clinical Practice, MSc\": \"eduhea/aphsc/\",\r\n"
				+ "  \"Enhanced Professional Practice, PGDip\": \"eduhea/enhanced-professional-practice-pgdip/\",\r\n"
				+ "  \"Global Health Management, MSc\": \"eduhea/global-health-management-msc/\",\r\n"
				+ "  \"Global Public Health MSc\": \"eduhea/msc-global-public-health/\",\r\n"
				+ "  \"Healthcare Practice, MA\": \"eduhea/healthcare-practice-ma/\",\r\n"
				+ "  \"Interprofessional Healthcare Simulation, MSc\": \"eduhea/interprofessional-healthcare-simulation-msc/\",\r\n"
				+ "  \"Applied Food Safety and Quality Management with Industrial Practice, MSc\": \"engsci/applied-food-safety-and-quality-management-with-industrial-practice-msc/\",\r\n"
				+ "  \"Applied Food Safety and Quality Management, PGDip/MSc\": \"engsci/foodsaf/\",\r\n"
				+ "  \"Food Innovation with Industrial Practice, MSc\": \"engsci/food-innovation-with-industrial-practice-msc/\",\r\n"
				+ "  \"Food Innovation, MSc\": \"engsci/foodinnov/\",\r\n"
				+ "  \"Food Safety and Quality Management e-learning, PGCert/PGDip/MSc\": \"engsci/food-safety-and-quality-management-distance-learning/\",\r\n"
				+ "  \"Human Resource Management, MA\": \"bus/hrm/\",\r\n"
				+ "  \"International & Commercial Law, LLM\": \"ach/international-and-commercial-law-llm/\",\r\n"
				+ "  \"Law, Regulation and the International Financial System, MSc\": \"ach/law-regulation-international-financial-system-msc/\",\r\n"
				+ "  \"Strategic Advertising and Marketing Communications, MA\": \"bus/stratmcomms/\",\r\n"
				+ "  \"Strategic Marketing, MA\": \"bus/stratm/\",\r\n"
				+ "  \"Mechanical and Manufacturing Engineering with Industrial Practice, MSc\": \"engsci/mechanical-and-manufacturing-engineering-with-industrial-practice-msc/\",\r\n"
				+ "  \"Mechanical and Manufacturing Engineering, MSc\": \"engsci/mechmaneng/\",\r\n"
				+ "  \"Product Design Engineering, MSc\": \"engsci/product-design-engineering-msc/\",\r\n"
				+ "  \"Advanced Clinical Practitioner, MSc (Degree Apprenticeship)\": \"eduhea/advanced-clinical-practitioner-msc-hons-degree-apprenticeship/\",\r\n"
				+ "  \"Nursing (Adult Nursing), MSc\": \"eduhea/nursing-adult-nursing/\",\r\n"
				+ "  \"Physician Associate, MSc (Degree Apprenticeship)\": \"eduhea/physician-associate-msc-degree-apprenticeship/\",\r\n"
				+ "  \"Secondary Education Physical Education, PGCE\": \"eduhea/pe/\",\r\n"
				+ "  \"Sport and Exercise Psychology, MSc\": \"eduhea/sport-and-exercise-psychology/\",\r\n"
				+ "  \"Strength and Conditioning, MSc\": \"eduhea/strengthcond/\",\r\n"
				+ "  \"Formulation Science, MSc\": \"engsci/fs/\",\r\n"
				+ "  \"Pharmaceutical Science, MSc/PGDip\": \"engsci/mps/\",\r\n"
				+ "  \"Pharmaceutical Sciences with Industrial Practice, MSc\": \"engsci/msc-pharmaceutical-sciences-with-industrial-practice/\",\r\n"
				+ "  \"General Pharmacy Practice, PGCert/PGDip/MSc\": \"engsci/gpp/\",\r\n"
				+ "  \"Independent/Supplementary Prescribing, PGCert\": \"engsci/isp/\",\r\n"
				+ "  \"Medicines Optimisation, PGCert/PGDip/MSc\": \"engsci/medopt/\",\r\n"
				+ "  \"Child and Adolescent Psychology, MSc\": \"eduhea/childadolpsych/\",\r\n"
				+ "  \"Forensic Psychology, MSc\": \"eduhea/forensic-psychology-msc/\",\r\n"
				+ "  \"Occupational Psychology, MSc\": \"eduhea/occupational-psychology-msc/\",\r\n"
				+ "  \"Psychology, MSc (Conversion Degree)\": \"eduhea/psych/\",\r\n"
				+ "  \"Therapeutic Counselling, MSc\": \"eduhea/therc/\",\r\n"
				+ "  \"Social Work, PGDip/MA\": \"eduhea/hsc/\",\r\n"
				+ "  \"Assessment Only Route to Qualified Teacher Status\": \"eduhea/assessment-only/\",\r\n"
				+ "  \"PGCE Further Education & Skills Sector\": \"eduhea/postgraduate-certificate-education/\",\r\n"
				+ "  \"Primary Education, PGCE\": \"eduhea/pgce/\",\r\n"
				+ "  \"Primary Mathematics (Subject Specialist), PGCE\": \"eduhea/pgcemaths/\",\r\n"
				+ "  \"International Events Management, MA\": \"bus/eveman/\",\r\n"
				+ "  \"International Tourism and Hospitality Management, MA\": \"bus/inttour/\"\r\n"
				+ "}";
		UniversityDetails undetails = new UniversityDetails(13, "University of Greenwich", "Old Royal Naval College, Park Row, London SE10 9LS", courses, 4, description, images, "+44(0)20 8331 8000.", reviews,courseMap);
				
		this.universityRepo.save(undetails);
	
	return this.universityRepo.findAllByOrderByRatingDesc();
	}
	public List<UniversityDetails> getUniversitiesByName(String universityName, String courseName, String department) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UniversityDetails> query = cb.createQuery(UniversityDetails.class);
        Root<UniversityDetails> university = query.from(UniversityDetails.class);
        
        MapJoin<UniversityDetails, String, String[]> courseJoin = university.join(UniversityDetails_.courses, JoinType.INNER);
        Path<String> departmentPath= courseJoin.key();
        Expression<String> coursePath= courseJoin.value().as(String.class);
        List<Predicate> predicates = new ArrayList<>();
        if(!universityName.equalsIgnoreCase("undefined")) {
        	Path<String> uName = university.get("universityName");
        	predicates.add(cb.like(uName, "%"+universityName+"%"));
        }
        if(!courseName.equalsIgnoreCase("undefined")) {
        	predicates.add(cb.like(coursePath, "%"+courseName+"%"));
        }
        if (!department.equalsIgnoreCase("undefined")) {
        	predicates.add(cb.like(departmentPath, "%"+department+"%"));
        }
        query.select(university).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        query.orderBy(cb.desc(university.get("rating")));

        return entityManager.createQuery(query).getResultList();
	}
	
	public Optional<UniversityDetails> getUniversityById(String unId) {
		return this.universityRepo.findById(Long.parseLong(unId));
		
	}

	
}

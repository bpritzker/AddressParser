package org.benp.addressparser.data;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 * This is a mapping of ALL known Street Suffix. I found this mapping at the USPS site.
 * I was going to load it from a file but since I don't expect it 
 * to change very often I'm going to hard code it.
 * 
 * http://pe.usps.gov/text/pub28/28apc_002.htm
 * 
 * TODO: Convert these to ApStreeSuffixValue and ApStreeSuffixValues (allows for international) 
 * 
 */
public enum ApStreetPostTypeEnum {
	
	ALLEY("ALLEY", "ALY", new HashSet<String>(Arrays.asList("ALY","ALLY","ALLEE","ALLEY"))),
	ANEX("ANEX", "ANX", new HashSet<String>(Arrays.asList("ANX","ANNEX","ANNX","ANEX"))),
	ARCADE("ARCADE", "ARC", new HashSet<String>(Arrays.asList("ARC","ARCADE"))),
	AVENUE("AVENUE", "AVE", new HashSet<String>(Arrays.asList("AVN","AV","AVENUE","AVE","AVNUE","AVEN","AVENU"))),
	BAYOU("BAYOU", "BYU", new HashSet<String>(Arrays.asList("BAYOO","BYU","BAYOU"))),
	BEACH("BEACH", "BCH", new HashSet<String>(Arrays.asList("BCH","BEACH"))),
	BEND("BEND", "BND", new HashSet<String>(Arrays.asList("BND","BEND"))),
	BLUFF("BLUFF", "BLF", new HashSet<String>(Arrays.asList("BLUF","BLUFF","BLF"))),
	BLUFFS("BLUFFS", "BLFS", new HashSet<String>(Arrays.asList("BLUFFS","BLFS"))),
	BOTTOM("BOTTOM", "BTM", new HashSet<String>(Arrays.asList("BOTTM","BTM","BOT","BOTTOM"))),
	BOULEVARD("BOULEVARD", "BLVD", new HashSet<String>(Arrays.asList("BLVD","BOULEVARD","BOULV","BOUL"))),
	BRANCH("BRANCH", "BR", new HashSet<String>(Arrays.asList("BR","BRNCH","BRANCH"))),
	BRIDGE("BRIDGE", "BRG", new HashSet<String>(Arrays.asList("BRIDGE","BRG","BRDGE"))),
	BROOK("BROOK", "BRK", new HashSet<String>(Arrays.asList("BRK","BROOK"))),
	BROOKS("BROOKS", "BRKS", new HashSet<String>(Arrays.asList("BRKS","BROOKS"))),
	BURG("BURG", "BG", new HashSet<String>(Arrays.asList("BG","BURG"))),
	BURGS("BURGS", "BGS", new HashSet<String>(Arrays.asList("BGS","BURGS"))),
	BYPASS("BYPASS", "BYP", new HashSet<String>(Arrays.asList("BYPAS","BYP","BYPS","BYPASS","BYPA"))),
	CAMP("CAMP", "CP", new HashSet<String>(Arrays.asList("CAMP","CP","CMP"))),
	CANYON("CANYON", "CYN", new HashSet<String>(Arrays.asList("CNYN","CANYON","CANYN","CYN"))),
	CAPE("CAPE", "CPE", new HashSet<String>(Arrays.asList("CAPE","CPE"))),
	CAUSEWAY("CAUSEWAY", "CSWY", new HashSet<String>(Arrays.asList("CAUSEWAY","CAUSWA","CSWY"))),
	CENTER("CENTER", "CTR", new HashSet<String>(Arrays.asList("CNTR","CENTRE","CENT","CENTER","CENTR","CNTER","CEN","CTR"))),
	CENTERS("CENTERS", "CTRS", new HashSet<String>(Arrays.asList("CENTERS","CTRS"))),
	CIRCLE("CIRCLE", "CIR", new HashSet<String>(Arrays.asList("CIRC","CIRCLE","CRCL","CIRCL","CRCLE","CIR"))),
	CIRCLES("CIRCLES", "CIRS", new HashSet<String>(Arrays.asList("CIRS","CIRCLES"))),
	CLIFF("CLIFF", "CLF", new HashSet<String>(Arrays.asList("CLF","CLIFF"))),
	CLIFFS("CLIFFS", "CLFS", new HashSet<String>(Arrays.asList("CLIFFS","CLFS"))),
	CLUB("CLUB", "CLB", new HashSet<String>(Arrays.asList("CLUB","CLB"))),
	COMMON("COMMON", "CMN", new HashSet<String>(Arrays.asList("COMMON","CMN"))),
	COMMONS("COMMONS", "CMNS", new HashSet<String>(Arrays.asList("COMMONS","CMNS"))),
	CORNER("CORNER", "COR", new HashSet<String>(Arrays.asList("CORNER","COR"))),
	CORNERS("CORNERS", "CORS", new HashSet<String>(Arrays.asList("CORNERS","CORS"))),
	COURSE("COURSE", "CRSE", new HashSet<String>(Arrays.asList("CRSE","COURSE"))),
	COURT("COURT", "CT", new HashSet<String>(Arrays.asList("CT","COURT"))),
	COURTS("COURTS", "CTS", new HashSet<String>(Arrays.asList("COURTS","CTS"))),
	COVE("COVE", "CV", new HashSet<String>(Arrays.asList("CV","COVE"))),
	COVES("COVES", "CVS", new HashSet<String>(Arrays.asList("COVES","CVS"))),
	CREEK("CREEK", "CRK", new HashSet<String>(Arrays.asList("CREEK","CRK"))),
	CRESCENT("CRESCENT", "CRES", new HashSet<String>(Arrays.asList("CRSNT","CRSENT","CRES","CRESCENT"))),
	CREST("CREST", "CRST", new HashSet<String>(Arrays.asList("CRST","CREST"))),
	CROSSING("CROSSING", "XING", new HashSet<String>(Arrays.asList("CRSSNG","XING","CROSSING"))),
	CROSSROAD("CROSSROAD", "XRD", new HashSet<String>(Arrays.asList("CROSSROAD","XRD"))),
	CROSSROADS("CROSSROADS", "XRDS", new HashSet<String>(Arrays.asList("CROSSROADS","XRDS"))),
	CURVE("CURVE", "CURV", new HashSet<String>(Arrays.asList("CURV","CURVE"))),
	DALE("DALE", "DL", new HashSet<String>(Arrays.asList("DL","DALE"))),
	DAM("DAM", "DM", new HashSet<String>(Arrays.asList("DM","DAM"))),
	DIVIDE("DIVIDE", "DV", new HashSet<String>(Arrays.asList("DV","DVD","DIV","DIVIDE"))),
	DRIVE("DRIVE", "DR", new HashSet<String>(Arrays.asList("DRIV","DRIVE","DRV","DR"))),
	DRIVES("DRIVES", "DRS", new HashSet<String>(Arrays.asList("DRIVES","DRS"))),
	ESTATE("ESTATE", "EST", new HashSet<String>(Arrays.asList("EST","ESTATE"))),
	ESTATES("ESTATES", "ESTS", new HashSet<String>(Arrays.asList("ESTS","ESTATES"))),
	EXPRESSWAY("EXPRESSWAY", "EXPY", new HashSet<String>(Arrays.asList("EXPR","EXPRESS","EXP","EXPW","EXPY","EXPRESSWAY"))),
	EXTENSION("EXTENSION", "EXT", new HashSet<String>(Arrays.asList("EXTN","EXT","EXTNSN","EXTENSION"))),
	EXTENSIONS("EXTENSIONS", "EXTS", new HashSet<String>(Arrays.asList("EXTENSIONS","EXTS"))),
	FALL("FALL", "FALL", new HashSet<String>(Arrays.asList("FALL"))),
	FALLS("FALLS", "FLS", new HashSet<String>(Arrays.asList("FALLS","FLS"))),
	FERRY("FERRY", "FRY", new HashSet<String>(Arrays.asList("FRRY","FERRY","FRY"))),
	FIELD("FIELD", "FLD", new HashSet<String>(Arrays.asList("FIELD","FLD"))),
	FIELDS("FIELDS", "FLDS", new HashSet<String>(Arrays.asList("FIELDS","FLDS"))),
	FLAT("FLAT", "FLT", new HashSet<String>(Arrays.asList("FLAT","FLT"))),
	FLATS("FLATS", "FLTS", new HashSet<String>(Arrays.asList("FLTS","FLATS"))),
	FORD("FORD", "FRD", new HashSet<String>(Arrays.asList("FRD","FORD"))),
	FORDS("FORDS", "FRDS", new HashSet<String>(Arrays.asList("FRDS","FORDS"))),
	FOREST("FOREST", "FRST", new HashSet<String>(Arrays.asList("FORESTS","FOREST","FRST"))),
	FORGE("FORGE", "FRG", new HashSet<String>(Arrays.asList("FORGE","FRG","FORG"))),
	FORGES("FORGES", "FRGS", new HashSet<String>(Arrays.asList("FORGES","FRGS"))),
	FORK("FORK", "FRK", new HashSet<String>(Arrays.asList("FORK","FRK"))),
	FORKS("FORKS", "FRKS", new HashSet<String>(Arrays.asList("FRKS","FORKS"))),
	FORT("FORT", "FT", new HashSet<String>(Arrays.asList("FT","FORT","FRT"))),
	FREEWAY("FREEWAY", "FWY", new HashSet<String>(Arrays.asList("FWY","FREEWY","FREEWAY","FRWY","FRWAY"))),
	GARDEN("GARDEN", "GDN", new HashSet<String>(Arrays.asList("GRDN","GARDEN","GRDEN","GDN","GARDN"))),
	GARDENS("GARDENS", "GDNS", new HashSet<String>(Arrays.asList("GRDNS","GDNS","GARDENS"))),
	GATEWAY("GATEWAY", "GTWY", new HashSet<String>(Arrays.asList("GATWAY","GTWY","GATEWY","GATEWAY","GTWAY"))),
	GLEN("GLEN", "GLN", new HashSet<String>(Arrays.asList("GLN","GLEN"))),
	GLENS("GLENS", "GLNS", new HashSet<String>(Arrays.asList("GLNS","GLENS"))),
	GREEN("GREEN", "GRN", new HashSet<String>(Arrays.asList("GRN","GREEN"))),
	GREENS("GREENS", "GRNS", new HashSet<String>(Arrays.asList("GRNS","GREENS"))),
	GROVE("GROVE", "GRV", new HashSet<String>(Arrays.asList("GROV","GROVE","GRV"))),
	GROVES("GROVES", "GRVS", new HashSet<String>(Arrays.asList("GRVS","GROVES"))),
	HARBOR("HARBOR", "HBR", new HashSet<String>(Arrays.asList("HRBOR","HARB","HARBR","HBR","HARBOR"))),
	HARBORS("HARBORS", "HBRS", new HashSet<String>(Arrays.asList("HARBORS","HBRS"))),
	HAVEN("HAVEN", "HVN", new HashSet<String>(Arrays.asList("HVN","HAVEN"))),
	HEIGHTS("HEIGHTS", "HTS", new HashSet<String>(Arrays.asList("HEIGHTS","HTS","HT"))),
	HIGHWAY("HIGHWAY", "HWY", new HashSet<String>(Arrays.asList("HWAY","HIGHWAY","HIWY","HIWAY","HIGHWY","HWY"))),
	HILL("HILL", "HL", new HashSet<String>(Arrays.asList("HILL","HL"))),
	HILLS("HILLS", "HLS", new HashSet<String>(Arrays.asList("HILLS","HLS"))),
	HOLLOW("HOLLOW", "HOLW", new HashSet<String>(Arrays.asList("HOLWS","HOLLOW","HLLW","HOLLOWS","HOLW"))),
	INLET("INLET", "INLT", new HashSet<String>(Arrays.asList("INLT","INLET"))),
	ISLAND("ISLAND", "IS", new HashSet<String>(Arrays.asList("IS","ISLND","ISLAND"))),
	ISLANDS("ISLANDS", "ISS", new HashSet<String>(Arrays.asList("ISLNDS","ISS","ISLANDS"))),
	ISLE("ISLE", "ISLE", new HashSet<String>(Arrays.asList("ISLES","ISLE"))),
	JUNCTION("JUNCTION", "JCT", new HashSet<String>(Arrays.asList("JCTION","JUNCTN","JCT","JCTN","JUNCTION","JUNCTON"))),
	JUNCTIONS("JUNCTIONS", "JCTS", new HashSet<String>(Arrays.asList("JCTS","JCTNS","JUNCTIONS"))),
	KEY("KEY", "KY", new HashSet<String>(Arrays.asList("KY","KEY"))),
	KEYS("KEYS", "KYS", new HashSet<String>(Arrays.asList("KYS","KEYS"))),
	KNOLL("KNOLL", "KNL", new HashSet<String>(Arrays.asList("KNOL","KNOLL","KNL"))),
	KNOLLS("KNOLLS", "KNLS", new HashSet<String>(Arrays.asList("KNLS","KNOLLS"))),
	LAKE("LAKE", "LK", new HashSet<String>(Arrays.asList("LAKE","LK"))),
	LAKES("LAKES", "LKS", new HashSet<String>(Arrays.asList("LKS","LAKES"))),
	LAND("LAND", "LAND", new HashSet<String>(Arrays.asList("LAND"))),
	LANDING("LANDING", "LNDG", new HashSet<String>(Arrays.asList("LNDG","LNDNG","LANDING"))),
	LANE("LANE", "LN", new HashSet<String>(Arrays.asList("LN","LANE"))),
	LIGHT("LIGHT", "LGT", new HashSet<String>(Arrays.asList("LGT","LIGHT"))),
	LIGHTS("LIGHTS", "LGTS", new HashSet<String>(Arrays.asList("LGTS","LIGHTS"))),
	LOAF("LOAF", "LF", new HashSet<String>(Arrays.asList("LOAF","LF"))),
	LOCK("LOCK", "LCK", new HashSet<String>(Arrays.asList("LCK","LOCK"))),
	LOCKS("LOCKS", "LCKS", new HashSet<String>(Arrays.asList("LCKS","LOCKS"))),
	LODGE("LODGE", "LDG", new HashSet<String>(Arrays.asList("LDGE","LDG","LODGE","LODG"))),
	LOOP("LOOP", "LOOP", new HashSet<String>(Arrays.asList("LOOP","LOOPS"))),
	MALL("MALL", "MALL", new HashSet<String>(Arrays.asList("MALL"))),
	MANOR("MANOR", "MNR", new HashSet<String>(Arrays.asList("MANOR","MNR"))),
	MANORS("MANORS", "MNRS", new HashSet<String>(Arrays.asList("MNRS","MANORS"))),
	MEADOW("MEADOW", "MDW", new HashSet<String>(Arrays.asList("MEADOW","MDW"))),
	MEADOWS("MEADOWS", "MDWS", new HashSet<String>(Arrays.asList("MEDOWS","MDWS","MDW","MEADOWS"))),
	MEWS("MEWS", "MEWS", new HashSet<String>(Arrays.asList("MEWS"))),
	MILL("MILL", "ML", new HashSet<String>(Arrays.asList("ML","MILL"))),
	MILLS("MILLS", "MLS", new HashSet<String>(Arrays.asList("MILLS","MLS"))),
	MISSION("MISSION", "MSN", new HashSet<String>(Arrays.asList("MISSION","MISSN","MSSN","MSN"))),
	MOTORWAY("MOTORWAY", "MTWY", new HashSet<String>(Arrays.asList("MOTORWAY","MTWY"))),
	MOUNT("MOUNT", "MT", new HashSet<String>(Arrays.asList("MNT","MOUNT","MT"))),
	MOUNTAIN("MOUNTAIN", "MTN", new HashSet<String>(Arrays.asList("MOUNTIN","MNTAIN","MTIN","MNTN","MOUNTAIN","MTN"))),
	MOUNTAINS("MOUNTAINS", "MTNS", new HashSet<String>(Arrays.asList("MNTNS","MOUNTAINS","MTNS"))),
	NECK("NECK", "NCK", new HashSet<String>(Arrays.asList("NECK","NCK"))),
	ORCHARD("ORCHARD", "ORCH", new HashSet<String>(Arrays.asList("ORCHRD","ORCH","ORCHARD"))),
	OVAL("OVAL", "OVAL", new HashSet<String>(Arrays.asList("OVL","OVAL"))),
	OVERPASS("OVERPASS", "OPAS", new HashSet<String>(Arrays.asList("OPAS","OVERPASS"))),
	PARK("PARK", "PARK", new HashSet<String>(Arrays.asList("PARK","PRK"))),
	PARKS("PARKS", "PARK", new HashSet<String>(Arrays.asList("PARK","PARKS"))),
	// TODO: These too have the same standard abbriv????
	PARKWAY("PARKWAY", "PKWY", new HashSet<String>(Arrays.asList("PKWY","PARKWAY","PARKWY","PKWAY","PKY"))),
	PARKWAYS("PARKWAYS", "PKWY", new HashSet<String>(Arrays.asList("PKWYS","PKWY","PARKWAYS"))),
	PASS("PASS", "PASS", new HashSet<String>(Arrays.asList("PASS"))),
	PASSAGE("PASSAGE", "PSGE", new HashSet<String>(Arrays.asList("PSGE","PASSAGE"))),
	PATH("PATH", "PATH", new HashSet<String>(Arrays.asList("PATH","PATHS"))),
	PIKE("PIKE", "PIKE", new HashSet<String>(Arrays.asList("PIKE","PIKES"))),
	PINE("PINE", "PNE", new HashSet<String>(Arrays.asList("PINE","PNE"))),
	PINES("PINES", "PNES", new HashSet<String>(Arrays.asList("PNES","PINES"))),
	PLACE("PLACE", "PL", new HashSet<String>(Arrays.asList("PL","PLACE"))),
	PLAIN("PLAIN", "PLN", new HashSet<String>(Arrays.asList("PLN","PLAIN"))),
	PLAINS("PLAINS", "PLNS", new HashSet<String>(Arrays.asList("PLAINS","PLNS"))),
	PLAZA("PLAZA", "PLZ", new HashSet<String>(Arrays.asList("PLZA","PLAZA","PLZ"))),
	POINT("POINT", "PT", new HashSet<String>(Arrays.asList("PT","POINT"))),
	POINTS("POINTS", "PTS", new HashSet<String>(Arrays.asList("POINTS","PTS"))),
	PORT("PORT", "PRT", new HashSet<String>(Arrays.asList("PORT","PRT"))),
	PORTS("PORTS", "PRTS", new HashSet<String>(Arrays.asList("PRTS","PORTS"))),
	PRAIRIE("PRAIRIE", "PR", new HashSet<String>(Arrays.asList("PRR","PR","PRAIRIE"))),
	RADIAL("RADIAL", "RADL", new HashSet<String>(Arrays.asList("RADIAL","RADIEL","RAD","RADL"))),
	RAMP("RAMP", "RAMP", new HashSet<String>(Arrays.asList("RAMP"))),
	RANCH("RANCH", "RNCH", new HashSet<String>(Arrays.asList("RNCHS","RANCHES","RANCH","RNCH"))),
	RAPID("RAPID", "RPD", new HashSet<String>(Arrays.asList("RPD","RAPID"))),
	RAPIDS("RAPIDS", "RPDS", new HashSet<String>(Arrays.asList("RAPIDS","RPDS"))),
	REST("REST", "RST", new HashSet<String>(Arrays.asList("REST","RST"))),
	RIDGE("RIDGE", "RDG", new HashSet<String>(Arrays.asList("RDGE","RDG","RIDGE"))),
	RIDGES("RIDGES", "RDGS", new HashSet<String>(Arrays.asList("RDGS","RIDGES"))),
	RIVER("RIVER", "RIV", new HashSet<String>(Arrays.asList("RIVR","RVR","RIVER","RIV"))),
	ROAD("ROAD", "RD", new HashSet<String>(Arrays.asList("RD","ROAD"))),
	ROADS("ROADS", "RDS", new HashSet<String>(Arrays.asList("RDS","ROADS"))),
	ROUTE("ROUTE", "RTE", new HashSet<String>(Arrays.asList("ROUTE","RTE"))),
	ROW("ROW", "ROW", new HashSet<String>(Arrays.asList("ROW"))),
	RUE("RUE", "RUE", new HashSet<String>(Arrays.asList("RUE"))),
	RUN("RUN", "RUN", new HashSet<String>(Arrays.asList("RUN"))),
	SHOAL("SHOAL", "SHL", new HashSet<String>(Arrays.asList("SHOAL","SHL"))),
	SHOALS("SHOALS", "SHLS", new HashSet<String>(Arrays.asList("SHOALS","SHLS"))),
	SHORE("SHORE", "SHR", new HashSet<String>(Arrays.asList("SHR","SHORE","SHOAR"))),
	SHORES("SHORES", "SHRS", new HashSet<String>(Arrays.asList("SHRS","SHOARS","SHORES"))),
	SKYWAY("SKYWAY", "SKWY", new HashSet<String>(Arrays.asList("SKWY","SKYWAY"))),
	SPRING("SPRING", "SPG", new HashSet<String>(Arrays.asList("SPG","SPRNG","SPNG","SPRING"))),
	SPRINGS("SPRINGS", "SPGS", new HashSet<String>(Arrays.asList("SPRNGS","SPGS","SPRINGS","SPNGS"))),
	SPUR("SPUR", "SPUR", new HashSet<String>(Arrays.asList("SPUR"))),
	SPURS("SPURS", "SPUR", new HashSet<String>(Arrays.asList("SPURS","SPUR"))),
	SQUARE("SQUARE", "SQ", new HashSet<String>(Arrays.asList("SQRE","SQ","SQR","SQUARE","SQU"))),
	SQUARES("SQUARES", "SQS", new HashSet<String>(Arrays.asList("SQUARES","SQS","SQRS"))),
	STATION("STATION", "STA", new HashSet<String>(Arrays.asList("STATN","STA","STATION","STN"))),
	STRAVENUE("STRAVENUE", "STRA", new HashSet<String>(Arrays.asList("STRAV","STRA","STRVN","STRAVEN","STRAVN","STRAVENUE","STRVNUE"))),
	STREAM("STREAM", "STRM", new HashSet<String>(Arrays.asList("STRM","STREAM","STREME"))),
	STREET("STREET", "ST", new HashSet<String>(Arrays.asList("STR","STREET","ST","STRT"))),
	STREETS("STREETS", "STS", new HashSet<String>(Arrays.asList("STS","STREETS"))),
	SUMMIT("SUMMIT", "SMT", new HashSet<String>(Arrays.asList("SUMITT","SUMIT","SUMMIT","SMT"))),
	TERRACE("TERRACE", "TER", new HashSet<String>(Arrays.asList("TERR","TER","TERRACE"))),
	THROUGHWAY("THROUGHWAY", "TRWY", new HashSet<String>(Arrays.asList("THROUGHWAY","TRWY"))),
	TRACE("TRACE", "TRCE", new HashSet<String>(Arrays.asList("TRCE","TRACES","TRACE"))),
	// TODO: the next two might have some overlapping values in the array????
	// Is TRK a TRACK or TRAK????
	TRACK("TRACK", "TRACK", new HashSet<String>(Arrays.asList("TRACK","TRK","TRKS","TRACKS"))),
	TRAK("TRAK", "TRAK", new HashSet<String>(Arrays.asList("TRAK"))),
	TRAFFICWAY("TRAFFICWAY", "TRFY", new HashSet<String>(Arrays.asList("TRAFFICWAY","TRFY"))),
	TRAIL("TRAIL", "TRL", new HashSet<String>(Arrays.asList("TRAIL","TRLS","TRL","TRAILS"))),
	TRAILER("TRAILER", "TRLR", new HashSet<String>(Arrays.asList("TRLR","TRLRS","TRAILER"))),
	TUNNEL("TUNNEL", "TUNL", new HashSet<String>(Arrays.asList("TUNEL","TUNLS","TUNNL","TUNNEL","TUNL","TUNNELS"))),
	TURNPIKE("TURNPIKE", "TPKE", new HashSet<String>(Arrays.asList("TRNPK","TPKE","TURNPK","TURNPIKE"))),
	UNDERPASS("UNDERPASS", "UPAS", new HashSet<String>(Arrays.asList("UPAS","UNDERPASS"))),
	UNION("UNION", "UN", new HashSet<String>(Arrays.asList("UN","UNION"))),
	UNIONS("UNIONS", "UNS", new HashSet<String>(Arrays.asList("UNIONS","UNS"))),
	VALLEY("VALLEY", "VLY", new HashSet<String>(Arrays.asList("VALLEY","VLY","VALLY","VLLY"))),
	VALLEYS("VALLEYS", "VLYS", new HashSet<String>(Arrays.asList("VALLEYS","VLYS"))),
	VIADUCT("VIADUCT", "VIA", new HashSet<String>(Arrays.asList("VIADUCT","VIA","VDCT","VIADCT"))),
	VIEW("VIEW", "VW", new HashSet<String>(Arrays.asList("VIEW","VW"))),
	VIEWS("VIEWS", "VWS", new HashSet<String>(Arrays.asList("VIEWS","VWS"))),
	VILLAGE("VILLAGE", "VLG", new HashSet<String>(Arrays.asList("VILLAG","VILL","VILLAGE","VLG","VILLIAGE","VILLG"))),
	VILLAGES("VILLAGES", "VLGS", new HashSet<String>(Arrays.asList("VLGS","VILLAGES"))),
	VILLE("VILLE", "VL", new HashSet<String>(Arrays.asList("VL","VILLE"))),
	VISTA("VISTA", "VIS", new HashSet<String>(Arrays.asList("VST","VSTA","VIS","VISTA","VIST"))),
	WALK("WALK", "WALK", new HashSet<String>(Arrays.asList("WALK"))),
	WALKS("WALKS", "WALK", new HashSet<String>(Arrays.asList("WALK","WALKS"))),
	WALL("WALL", "WALL", new HashSet<String>(Arrays.asList("WALL"))),
	WAY("WAY", "WAY", new HashSet<String>(Arrays.asList("WY","WAY"))),
	WAYS("WAYS", "WAYS", new HashSet<String>(Arrays.asList("WAYS"))),
	WELL("WELL", "WL", new HashSet<String>(Arrays.asList("WELL","WL"))),
	WELLS("WELLS", "WLS", new HashSet<String>(Arrays.asList("WELLS","WLS")));

	


	private final String name;
	private final String standardAbbreviation;
	private final Set<String> commonAbbreviations;
	
	private ApStreetPostTypeEnum(String inName, String inStandardAbbreviation, Set<String> inCommonAbbreviations) {
		this.name = inName;
		this.standardAbbreviation = inStandardAbbreviation;
		this.commonAbbreviations = inCommonAbbreviations;
	}
	
	
	public String getName() {
		return name;
	}

	public String getStandardAbbreviation() {
		return standardAbbreviation;
	}

	public Set<String> getCommonAbbreviations() {
		return commonAbbreviations;
	}
	
	public static ApStreetPostTypeEnum fromCommonAbbreviation(String inCommonAbbreviation) {
		if (inCommonAbbreviation == null) {
			return null;
		}
		
		for (ApStreetPostTypeEnum currStreetSuffix : ApStreetPostTypeEnum.values()) {
			if (currStreetSuffix.getCommonAbbreviations().contains(inCommonAbbreviation)) {
				return currStreetSuffix;
			}
		}
		return null;
	}

}
